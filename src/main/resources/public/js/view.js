'use strict'

var app;

var frameCount=0;

document.onkeydown=function (event) {
    var e = event || window.event;
    if(e && e.keyCode===37){ // Left
        switchDirection(e.keyCode)
    }
    else if(e && e.keyCode===38){ // Up
        switchDirection(e.keyCode)
    }
    else if(e && e.keyCode===39){ // Right
        switchDirection(e.keyCode)
    }
    else if(e && e.keyCode===40){ // Down
        switchDirection(e.keyCode)
    }
};

function createApp(canvas) {
    var c = canvas.getContext("2d");

    //draw a dot
    var drawDot = function(x, y, radius, color) {
        c.fillStyle = color;
        c.beginPath();
        c.arc(x, y, radius, 0, 2 * Math.PI, false);
        c.closePath();
        c.fill();
    };
    //draw a wall
    var drawWall = function(startX, startY) {
        c.fillStyle="black";
        c.beginPath();
        c.moveTo(startX,startY);
        c.lineTo(startX+10,startY);
        c.lineTo(startX+10,startY+10);
        c.lineTo(startX,startY+10);
        c.closePath();
        c.fill();
        c.stroke();
    };
    //draw a exit
    var drawExit=function (startX,startY) {
        c.fillStyle="white";
        c.beginPath();
        c.moveTo(startX, startY);
        c.lineTo(startX, startY + 10);
        c.stroke();
    };
    //draw a image
    var drawImage=function (shape) {
        var image=new Image();
        image.src=location.href + shape.path;
        c.save();
        c.translate(shape.loc.x+5,shape.loc.y+5);
        // c.scale(shape.collisionIndex,1);
        // c.rotate(shape.angle);
        c.drawImage(image,-5,-5,10,10);
        c.restore();
    };
    //draw a Pacman
    var drawPacman = function(x, y, radius, color) {
        var mouth=Math.abs(frameCount%90-45);
        c.beginPath();
        c.arc(x, y, radius, mouth/180 * Math.PI, (mouth/180+1)* Math.PI, false);
        c.fillStyle = "rgb(255, 255, 0)";
        c.fill();
        c.beginPath();
        c.arc(x, y, radius, -(mouth/180+1) * Math.PI, -(mouth/180) * Math.PI, false);
        c.fill();
        // c.beginPath();
        // c.arc(x, y-25, 10, 0, 2 * Math.PI, false);
        // c.fillStyle = "rgb(0, 0, 0)";
        // c.fill();
    };
    
    return {
        drawExit:drawExit,
        drawDot: drawDot,
        drawWall: drawWall,
        drawPacman:drawPacman,
        drawImage:drawImage,
        dims: {height: canvas.height, width: canvas.width}
    }
}

/**
 * Entry point into app
 */
window.onload = function() {
    reset();
    app = createApp(document.querySelector("canvas"));
    canvasDims();
    setInterval(updateGameWorld, 100);
}

/**
 * initialize the game
 */

function initGame() {
    $.get("/resetGame", function (data, status) {
        var pObs = data.obs;
        pObs.forEach(function(element) {
            if (element.type === "Wall"){
                app.drawWall(element.loc.x,element.loc.y)
            }
            else if(element.type === "Exit") {
                app.drawExit(element.loc.x, element.loc.y);
            }
            else if(element.type==="Pacman"){
                app.drawPacman(element.loc.x,element.loc.y,element.radius,element.color)
            }
            else if(element.type==="Ghost"){
                app.drawImage(element)
            }
            else if(element.type==="SmallDot"){
                app.drawDot(element.loc.x,element.loc.y,element.radius,element.color)
            }
            else if(element.type==="BigDot"){
                app.drawDot(element.loc.x,element.loc.y,element.radius,element.color)
            }
            else if(element.type==="Fruit"){
                app.drawImage(element)
            }
        });
    }, "json");
}

/**
 *   update the ball and inner walls
 */
function updateGameWorld() {
    $.get("/updateGame", function(data, status) {
        app.clear();
        frameCount=frameCount+10;
        var pObs = data.obs;
        pObs.forEach(function(element) {
            if (element.type === "Wall"){
                app.drawWall(element.loc.x,element.loc.y)
            }
            else if(element.type === "Exit") {
                app.drawExit(element.loc.x, element.loc.y);
            }
            else if(element.type==="Pacman"){
                app.drawPacman(element.loc.x,element.loc.y,element.radius,element.color)
            }
            else if(element.type==="Ghost"){
                app.drawImage(element)
            }
            else if(element.type==="SmallDot"){
                app.drawDot(element.loc.x,element.loc.y,element.radius,element.color)
            }
            else if(element.type==="BigDot"){
                app.drawDot(element.loc.x,element.loc.y,element.radius,element.color)
            }
            else if(element.type==="Fruit"){
                app.drawImage(element)
            }
        });
    }, "json");
}


/**
 * Pass along the canvas dimensions
 */
function canvasDims() {
    $.get("/canvasDims", {height: app.dims.height, width: app.dims.width});
}

/**
 * reset the game
 */
function reset() {
    frameCount=0;
    app.clear();
    initGame();
}

/**
 * switch the direction
 */
function switchDirection(keycode) {
    $.post("/switchDirection", {keycode:keycode }, function (data, status) {
    }, "json");
}