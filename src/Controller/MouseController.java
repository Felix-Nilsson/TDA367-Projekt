package Controller;

import java.awt.*;

public class MouseController {
    public int getMouseX(){
        return MouseInfo.getPointerInfo().getLocation().x;

    }
    public int getMouseY(){
        return MouseInfo.getPointerInfo().getLocation().y;
    }
}
