'use strict'

var app;

var frameCount=0;

/**
 * Get the direction from the keyboard
 * @param event
 */
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

/**
 * Draw on the canvas.
 * @param canvas
 * @returns {{drawExit: drawExit, drawDot: drawDot, drawWall: drawWall, drawPacMan: drawPacMan, drawImage: drawImage, clear: clear, dims: {height: *, width: *}}}
 */
function createApp(canvas) {
    var c = canvas.getContext("2d");

    //draw a dot
    var drawDot = function(x, y, size) {
        c.fillStyle = "rgb(0, 0, 0)";
        c.beginPath();
        c.arc(x, y, size/2, 0, 2 * Math.PI, false);
        c.closePath();
        c.fill();
    };

    //draw a wall
    var drawWall = function(startX, startY,size) {
        c.fillStyle="black";
        c.beginPath();
        c.moveTo(startX,startY);
        c.lineTo(startX+size,startY);
        c.lineTo(startX+size,startY+size);
        c.lineTo(startX,startY+size);
        c.closePath();
        c.fill();
        c.stroke();
    };

    //draw a exit
    var drawExit=function (startX,startY,size) {
        c.fillStyle="white";
        c.beginPath();
        c.moveTo(startX, startY);
        c.lineTo(startX, startY + size);
        c.stroke();
    };

    //draw a image
    var drawImage=function (x,y,size,path) {
        var image=new Image();
        image.src=location.href + path;
        c.save();
        c.translate(x+size/2,y+size/2);
        // c.scale(shape.collisionIndex,1);
        // c.rotate(shape.angle);
        c.drawImage(image,-size/2,-size/2,size,size);
        c.restore();
    };

    //draw a pacman
    var drawPacMan = function(x, y, size) {
        var mouth=Math.abs(frameCount%90-45);
        c.beginPath();
        c.arc(x, y, size/2, mouth/180 * Math.PI, (mouth/180+1)* Math.PI, false);
        c.fillStyle = "rgb(255, 255, 0)";
        c.fill();
        c.beginPath();
        c.arc(x, y, size/2, -(mouth/180+1) * Math.PI, -(mouth/180) * Math.PI, false);
        c.fill();
        // c.beginPath();
        // c.arc(x, y-6, 2, 0, 2 * Math.PI, false);
        // c.fillStyle = "rgb(0, 0, 0)";
        // c.fill();
    };

    //clear
    var clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);
    };

    return {
        drawExit:drawExit,
        drawDot: drawDot,
        drawWall: drawWall,
        drawPacMan:drawPacMan,
        drawImage:drawImage,
        clear:clear,
        dims: {height: canvas.height, width: canvas.width}
    }
}

/**
 * Entry point into app
 */
window.onload = function() {
    app = createApp(document.querySelector("canvas"));
    reset();
    canvasDims();
    setInterval(updateGameWorld, 100);
}

/**
 * Initialize the game
 */

function initGame() {
    $.get("/resetGame", function (data, status) {
        var pObs = data.obs;
        pObs.forEach(function(element) {
            if (element.type === "wall"){
                app.drawWall(element.location.x,element.location.y)
            }
            else if(element.type === "exit") {
                app.drawExit(element.location.x, element.location.y);
            }
            else if(element.type==="pacman"){
                app.drawPacMan(element.location.x,element.location.y,element.size)
            }
            else if(element.type==="ghost"){
                if(element.color="red"){
                    app.drawImage(element.location.x,element.location.y,element.size,"reaGhostPath")
                }
                else if (element.color="pink"){
                    app.drawImage(element.location.x,element.location.y,element.size,"pinkGhostPath")
                }
                else if (element.color="orange"){
                    app.drawImage(element.location.x,element.location.y,element.size,"orangeGhostPath")
                }
                else if (element.color="blue"){
                    app.drawImage(element.location.x,element.location.y,element.size,"blueGhostPath")
                }
            }
            else if(element.type==="small_dot"){
                app.drawDot(element.location.x,element.location.y,element.size)
            }
            else if(element.type==="big_dot"){
                app.drawDot(element.location.x,element.location.y,element.size)
            }
        });
    }, "json");
}

/**
 *   Update all the observers.
 */
function updateGameWorld() {
    $.get("/updateGame", function(data, status) {
        app.clear();
        frameCount=frameCount+10;
        var pObs = data.obs;
        pObs.forEach(function(element) {
            if (element.type === "wall"){
                app.drawWall(element.location.x,element.location.y)
            }
            else if(element.type === "exit") {
                app.drawExit(element.location.x, element.location.y);
            }
            else if(element.type==="pacman"){
                app.drawPacMan(element.location.x,element.location.y,element.size)
            }
            else if(element.type==="ghost"){
                if(element.color="red"){
                    app.drawImage(element.location.x,element.location.y,element.size,"reaGhostPath")
                }
                else if (element.color="pink"){
                    app.drawImage(element.location.x,element.location.y,element.size,"pinkGhostPath")
                }
                else if (element.color="orange"){
                    app.drawImage(element.location.x,element.location.y,element.size,"orangeGhostPath")
                }
                else if (element.color="blue"){
                    app.drawImage(element.location.x,element.location.y,element.size,"blueGhostPath")
                }
            }
            else if(element.type==="small_dot"){
                app.drawDot(element.location.x,element.location.y,element.size)
            }
            else if(element.type==="big_dot"){
                app.drawDot(element.location.x,element.location.y,element.size)
            }
            else if(element.type==="fruit"){
                app.drawImage(element.location.x,element.location.y,element.size,"fruitPath")
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
 * Reset the game
 */
function reset() {
    frameCount=0;
    app.clear();
    initGame();
}

/**
 * Switch the moving direction for the Pacman
 */
function switchDirection(keycode) {
    $.post("/switchDirection", {keycode:keycode}, function (data, status) {
    }, "json");
}