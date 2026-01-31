package main;

import com.jogamp.opengl.GL2;

public class TextRenderer {
    
    private static void drawChar(GL2 gl, char c, float x, float y, float size) {
        gl.glBegin(GL2.GL_LINES);
        
        switch (c) {
            case 'G':
                // G
                gl.glVertex2f(x + size, y + size);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x, y);
                gl.glVertex2f(x, y);
                gl.glVertex2f(x + size, y);
                gl.glVertex2f(x + size, y);
                gl.glVertex2f(x + size, y + size/2);
                gl.glVertex2f(x + size, y + size/2);
                gl.glVertex2f(x + size/2, y + size/2);
                break;
            case 'A':
                // A
                gl.glVertex2f(x, y);
                gl.glVertex2f(x + size/2, y + size);
                gl.glVertex2f(x + size/2, y + size);
                gl.glVertex2f(x + size, y);
                gl.glVertex2f(x + size/4, y + size/2);
                gl.glVertex2f(x + 3*size/4, y + size/2);
                break;
            case 'M':
                // M
                gl.glVertex2f(x, y);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x + size/2, y + size/2);
                gl.glVertex2f(x + size/2, y + size/2);
                gl.glVertex2f(x + size, y + size);
                gl.glVertex2f(x + size, y + size);
                gl.glVertex2f(x + size, y);
                break;
            case 'E':
                // E
                gl.glVertex2f(x, y);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x + size, y + size);
                gl.glVertex2f(x, y + size/2);
                gl.glVertex2f(x + 3*size/4, y + size/2);
                gl.glVertex2f(x, y);
                gl.glVertex2f(x + size, y);
                break;
            case 'O':
                // O
                gl.glVertex2f(x, y);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x + size, y + size);
                gl.glVertex2f(x + size, y + size);
                gl.glVertex2f(x + size, y);
                gl.glVertex2f(x + size, y);
                gl.glVertex2f(x, y);
                break;
            case 'V':
                // V
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x + size/2, y);
                gl.glVertex2f(x + size/2, y);
                gl.glVertex2f(x + size, y + size);
                break;
            case 'R':
                // R
                gl.glVertex2f(x, y);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x + size, y + size);
                gl.glVertex2f(x + size, y + size);
                gl.glVertex2f(x + size, y + size/2);
                gl.glVertex2f(x + size, y + size/2);
                gl.glVertex2f(x, y + size/2);
                gl.glVertex2f(x + size/2, y + size/2);
                gl.glVertex2f(x + size, y);
                break;
            case 'P':
                // P
                gl.glVertex2f(x, y);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x + size, y + size);
                gl.glVertex2f(x + size, y + size);
                gl.glVertex2f(x + size, y + size/2);
                gl.glVertex2f(x + size, y + size/2);
                gl.glVertex2f(x, y + size/2);
                break;
            case 'S':
                // S
                gl.glVertex2f(x + size, y + size);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x, y + size/2);
                gl.glVertex2f(x, y + size/2);
                gl.glVertex2f(x + size, y + size/2);
                gl.glVertex2f(x + size, y + size/2);
                gl.glVertex2f(x + size, y);
                gl.glVertex2f(x + size, y);
                gl.glVertex2f(x, y);
                break;
            case 'T':
                // T
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x + size, y + size);
                gl.glVertex2f(x + size/2, y + size);
                gl.glVertex2f(x + size/2, y);
                break;
            case 'C':
                // C
                gl.glVertex2f(x + size, y + size);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x, y);
                gl.glVertex2f(x, y);
                gl.glVertex2f(x + size, y);
                break;
            case 'L':
                // L
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x, y);
                gl.glVertex2f(x, y);
                gl.glVertex2f(x + size, y);
                break;
            case 'I':
                // I
                gl.glVertex2f(x + size/2, y);
                gl.glVertex2f(x + size/2, y + size);
                break;
            case 'N':
                // N
                gl.glVertex2f(x, y);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x + size, y);
                gl.glVertex2f(x + size, y);
                gl.glVertex2f(x + size, y + size);
                break;
            case 'U':
                // U
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x, y);
                gl.glVertex2f(x, y);
                gl.glVertex2f(x + size, y);
                gl.glVertex2f(x + size, y);
                gl.glVertex2f(x + size, y + size);
                break;
            case 'W':
                // W
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x, y);
                gl.glVertex2f(x, y);
                gl.glVertex2f(x + size/2, y + size/2);
                gl.glVertex2f(x + size/2, y + size/2);
                gl.glVertex2f(x + size, y);
                gl.glVertex2f(x + size, y);
                gl.glVertex2f(x + size, y + size);
                break;
            case 'Y':
                // Y
                gl.glVertex2f(x, y + size);
                gl.glVertex2f(x + size/2, y + size/2);
                gl.glVertex2f(x + size, y + size);
                gl.glVertex2f(x + size/2, y + size/2);
                gl.glVertex2f(x + size/2, y + size/2);
                gl.glVertex2f(x + size/2, y);
                break;
            case ' ':
                break;
        }
        
        gl.glEnd();
    }
    
    public static void drawString(GL2 gl, String text, float x, float y, float size) {
        float currentX = x;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            drawChar(gl, c, currentX, y, size);
            currentX += size * 1.3f; 
        }
    }
}
