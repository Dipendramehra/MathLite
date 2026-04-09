function drawDiagram(config) {
    const canvas = document.getElementById("canvas");
    const ctx = canvas.getContext("2d");

    // Clear previous drawings
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    // 🔴 SET COLORS SO IT IS VISIBLE IN DARK MODE
    ctx.strokeStyle = "#FF5722"; // Deep Orange lines
    ctx.fillStyle = "#FF5722";   // Deep Orange text
    ctx.lineWidth = 3;           // Thicker lines

    // Draw the shape based on the object passed from Kotlin
    if (config.type === "triangle") {
        drawTriangle(ctx, config);
    } else if (config.type === "rectangle") {
        drawRectangle(ctx, config);
    } else if (config.type === "circle") {
        drawCircle(ctx, config);
    }
}
// ... rest of your shapes code stays exactly the same ...

// 🔺 Triangle
function drawTriangle(ctx, config) {
    const [A, B, C] = config.points;

    ctx.beginPath();
    ctx.moveTo(A.x, A.y);
    ctx.lineTo(B.x, B.y);
    ctx.lineTo(C.x, C.y);
    ctx.closePath();
    ctx.stroke();

    drawLabel(ctx, "A", A);
    drawLabel(ctx, "B", B);
    drawLabel(ctx, "C", C);
}

// ▭ Rectangle
function drawRectangle(ctx, config) {
    const { x, y, width, height } = config;

    ctx.strokeRect(x, y, width, height);

    drawLabel(ctx, "A", {x:x, y:y});
    drawLabel(ctx, "B", {x:x+width, y:y});
    drawLabel(ctx, "C", {x:x+width, y:y+height});
    drawLabel(ctx, "D", {x:x, y:y+height});
}

// ⚪ Circle
function drawCircle(ctx, config) {
    const { x, y, radius } = config;

    ctx.beginPath();
    ctx.arc(x, y, radius, 0, 2 * Math.PI);
    ctx.stroke();

    drawLabel(ctx, "O", {x:x, y:y});
}

// 🏷️ Label helper
function drawLabel(ctx, text, point) {
    ctx.font = "16px Arial";
    ctx.fillText(text, point.x + 5, point.y - 5);
}