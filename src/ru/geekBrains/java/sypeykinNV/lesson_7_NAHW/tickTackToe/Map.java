package ru.geekBrains.java.sypeykinNV.lesson_7_NAHW.tickTackToe;

import javax.swing.*;
import java.awt.*;

// класс игрового поля ( где находится игра)
public class Map extends JPanel {

    //константы с режимами игры
    public static final int MODE_HVH = 0;
    public static final int MODE_HVA = 1;


    Map(){
        setBackground(Color.darkGray);//задаем цвет фона
    }

    //заглушка
    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength){
        System.out.printf("mode:%d, size:%d, length:%d\n", gameMode, fieldSizeX, winLength);//Выдает инфо по запущеной игре
    }
}
