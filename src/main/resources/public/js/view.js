'use strict'

var app;

var frameCount = 0;

var redGhostImg = new Image();
redGhostImg.src = location.href + "redGhost.jpg";
var blueGhostImg = new Image();
blueGhostImg.src = location.href + "blueGhost.png";
var pinkGhostImg = new Image();
pinkGhostImg.src = location.href + "pinkGhost.png";
var orangeGhostImg = new Image();
orangeGhostImg.src = location.href + "orangeGhost.jpg";
var fruitImg = new Image();


/**
 * Get input from the keyboard and switch Pacman direction
 * @param event
 */
document.onkeydown = function(event) {
    var e = event || window.event;
    if (e && e.keyCode === 37) { // Left
        switchDirection(e.keyCode);
    }
    else if (e && e.keyCode === 38) { // Up
        switchDirection(e.keyCode);
    }
    else if (e && e.keyCode === 39) { // Right
        switchDirection(e.keyCode);
    }
    else if (e && e.keyCode === 40) { // Down
        switchDirection(e.keyCode);
    }
};

/**
 * Initialize the canvas
 * @param canvas
 * @returns functions to draw game objects
 */
function createApp(canvas) {
    var c = canvas.getContext("2d");

    // draw a dot
    var drawDot = function(x, y, size) {
        c.fillStyle = "gold";
        c.beginPath();
        c.arc(x + 10, y + 10, size/2, 0, 2 * Math.PI, false);
        c.closePath();
        c.fill();
    };

    // draw a wall
    var drawWall = function(startX, startY, size) {
        c.fillStyle = "blue";
        c.fillRect(startX, startY, size, size)
    };

    // draw an exit
    var drawExit = function (startX,startY,size) {
        c.fillStyle = "black";
        c.fillRect(startX, startY, size, size)
    };

    // draw an image
    var drawImage = function (x, y, size, image) {
        c.save();
        c.translate(x + size/2, y + size/2);
        c.drawImage(image, -size/2, -size/2, size, size);
        c.restore();
    };

    // draw Pacman
    var drawPacMan = function(x, y, size) {
        c.fillStyle = "yellow";
        var mouth = Math.abs(frameCount % 90 - 45);
        c.beginPath();
        c.arc(x, y, size/2, mouth/180 * Math.PI, (mouth/180+1)* Math.PI, false);
        c.fill();
        c.beginPath();
        c.arc(x, y, size/2, -(mouth/180+1) * Math.PI, -(mouth/180) * Math.PI, false);
        c.fill();
    };

    // clear canvas
    var clear = function() {
        c.clearRect(0, 0, canvas.width, canvas.height);
        c.fillStyle = "black";
        c.fillRect(0, 0, canvas.width, canvas.height - 30);
    };

    // draw score and lives
    var drawScore = function(score, lives) {
        c.font = "20px Arial";
        c.fillStyle = "black";
        c.fillText(score, 10, 360 );
        c.fillText(lives, 450, 360);

    };

    return {
        drawScore: drawScore,
        drawExit: drawExit,
        drawDot: drawDot,
        drawWall: drawWall,
        drawPacMan: drawPacMan,
        drawImage: drawImage,
        clear: clear,
        dims: { height: canvas.height, width: canvas.width }
    }
}

/**
 * Entry point into app
 */
window.onload = function() {
    app = createApp(document.querySelector("canvas"));
    resetGame();

    setInterval(updateGameWorld, 100);
}

/**
 * Initialize the game and pass canvas dimensions
 */
function resetGame() {
    frameCount = 0;
    app.clear();
    $.get("/resetGame", { height: app.dims.height, width: app.dims.width });
}

/**
 * Update and redraw all game objects
 */
function updateGameWorld() {
    $.get("/updateGame", function(data, status) {
        app.clear();
        frameCount = frameCount + 10;

        app.drawScore("Score : " + data.score, "Lives : " + data.lives);
        var pObs = data.obs;
        pObs.forEach(function(element) {
            if (element.type === "wall") {
                app.drawWall(element.location.x, element.location.y, element.size);
            }
            else if (element.type === "exit") {
                app.drawExit(element.location.x, element.location.y, element.size);
            }
            else if (element.type === "pacman") {
                app.drawPacMan(element.location.x, element.location.y, element.size);
            }
            else if(element.type === "ghost") {
                if (element.color === "red") {
                    app.drawImage(element.location.x, element.location.y, element.size, redGhostImg);
                }
                else if (element.color === "pink") {
                    app.drawImage(element.location.x, element.location.y, element.size, pinkGhostImg);
                }
                else if (element.color === "orange") {
                    app.drawImage(element.location.x, element.location.y, element.size, orangeGhostImg);
                }
                else if (element.color === "blue") {
                    app.drawImage(element.location.x, element.location.y, element.size, blueGhostImg);
                }
            }
            else if (element.type === "small_dot") {
                app.drawDot(element.location.x, element.location.y, element.size);
            }
            else if (element.type === "big_dot") {
                // big dot flashes every other update
                if (frameCount % 40 != 0) {
                    app.drawDot(element.location.x, element.location.y, element.size);
                }
            }
            else if (element.type === "fruit") {
                app.drawImage(element.location.x, element.location.y, element.size, "fruit.jpg");
            }
        });
    }, "json");
}

/**
 * Switch the moving direction for Pacman
 */
function switchDirection(keycode) {
    $.post("/switchDirection", { keycode: keycode }, function (data, status) {
    }, "json");
}

