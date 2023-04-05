package ru.geekBrains.java.sypeykinNV.lesson_7_NAHW.tickTackToe;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Класс окна с насройками режимов игры
public class SettingsWindow extends JFrame {

    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_HEIGHT = 270;
    private static final int MIN_WIN_LENGTH = 3;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final String FIELD_SIZE_PREFIX = "Fields size is: ";
    private static final String WIN_LENGTH_PREFIX = "Win length is: ";

    //элементы окна настроек
    private GameWindow gameWindow;
    private JRadioButton humVSAI;
    private JRadioButton humVSHum;
    private JSlider sliderWinLen;
    private JSlider sliderFieldSize;

    //Конструктор класса (на вход летит ссылка на объект класса gameWindow){это делается с целью того, чтобы объект класса
    // настроек окна был привязан к объекту классу самого окна}
    SettingsWindow(GameWindow gameWindow){
        this.gameWindow = gameWindow;
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        Rectangle gameWindowBounds = gameWindow.getBounds();//создаем экземпляр класса мониторящий границы окна
        // (это нужно для отображения окна настроек в центре экрана)
        //берем центр Х У и отнимаем половин от их длинны, чтобы задать точку для отображения окна настроек
        int posX = (int) gameWindowBounds.getCenterX() - WINDOW_WIDTH / 2;
        int posY = (int) gameWindowBounds.getCenterY() - WINDOW_HEIGHT / 2;
        setLocation(posX, posY);//вводим координат для отрисовки окна

        setResizable(false);
        setTitle("Creating new game");
        setLayout(new GridLayout(10, 1));

        addGameModeControls();
        addFieldControls();

        //кнопка запуска игры
        JButton btnStart = new JButton("Start new game");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStartClick();
            }
        });
        add(btnStart);
    }

    //Создает кнопки и слайдеры на окне настроек
    private void addGameModeControls(){
        add(new JLabel("Choose game mode"));
        humVSAI = new JRadioButton("Human vs AI", true);
        humVSHum = new JRadioButton("Human vs Human");
        ButtonGroup gameMode = new ButtonGroup();//класс группы кнопок
        gameMode.add(humVSAI);//добавили кнопки в группу и этого достаточно чтобы если мы выбираем одну вторая отключится
        gameMode.add(humVSHum);
        add(humVSAI);
        add(humVSHum);
    }

    private void addFieldControls(){
        JLabel lbFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);
        JLabel lbWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_WIN_LENGTH);
        sliderFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        sliderWinLen = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_SIZE, MIN_FIELD_SIZE);

        sliderWinLen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lbWinLength.setText(WIN_LENGTH_PREFIX + sliderWinLen.getValue());
            }
        });
        sliderFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //далее задаем максимальную выиграшную длинну для слайдера с выигрышной длинной
                // (чтобы этот пункт не уходил за границы поля)
                int current = sliderFieldSize.getValue();
                lbFieldSize.setText(FIELD_SIZE_PREFIX + current);
                sliderWinLen.setMaximum(current);
            }
        });

        add(new JLabel("Choose field size"));
        add(lbFieldSize);
        add(sliderFieldSize);
        add(new JLabel("Choose win length"));
        add(lbWinLength);
        add(sliderWinLen);
    }

    private void btnStartClick(){
        int gameMode;
        if (humVSAI.isSelected()){
            gameMode = Map.MODE_HVA;
        }else if (humVSHum.isSelected()){
            gameMode = Map.MODE_HVH;
        }else {
            throw new RuntimeException("Unexpected game mode ");
        }
        int fieldSize = sliderFieldSize.getValue();
        int winLen = sliderWinLen.getValue();

        gameWindow.applySettings(gameMode, fieldSize, winLen);//передает настройки в класс Мар
        // транзитом через класс GameWindow

        setVisible(false);
    }

    private void alternative_setLocationRelativeTo(GameWindow gameWindow) {
        setLocationRelativeTo(gameWindow);//центрируется относительно центра внешнего окна
    }
}
