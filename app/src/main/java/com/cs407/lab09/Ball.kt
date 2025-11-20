package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        // Start the ball in the center with zero velocity and zero acceleration
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        // --- X AXIS ---
        val v0x = velocityX
        val a0x = accX
        val a1x = xAcc

        val v1x = v0x + 0.5f * (a0x + a1x) * dT
        val dx = v0x * dT + (dT * dT) / 6f * (3f * a0x + a1x)

        velocityX = v1x
        posX += dx

        // --- Y AXIS ---
        val v0y = velocityY
        val a0y = accY
        val a1y = yAcc

        val v1y = v0y + 0.5f * (a0y + a1y) * dT
        val dy = v0y * dT + (dT * dT) / 6f * (3f * a0y + a1y)

        velocityY = v1y
        posY += dy

        // store new accelerations
        accX = xAcc
        accY = yAcc
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)

        // Left wall
        if (posX < 0f) {
            posX = 0f
            velocityX = 0f
            accX = 0f
        }

        // Right wall
        val maxX = backgroundWidth - ballSize
        if (posX > maxX) {
            posX = maxX
            velocityX = 0f
            accX = 0f
        }

        // Top wall
        if (posY < 0f) {
            posY = 0f
            velocityY = 0f
            accY = 0f
        }

        // Bottom wall
        val maxY = backgroundHeight - ballSize
        if (posY > maxY) {
            posY = maxY
            velocityY = 0f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)

        // set ball position to the center
        posX = (backgroundWidth - ballSize) / 2f
        posY = (backgroundHeight - ballSize) / 2f

        // zero out velocity and acceleration
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f

        // reset update state
        isFirstUpdate = true
    }
}