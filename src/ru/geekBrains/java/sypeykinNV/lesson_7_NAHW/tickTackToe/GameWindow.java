package ru.geekBrains.java.sypeykinNV.lesson_7_NAHW.tickTackToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    //Задаем константы параметров: ширина, длинна, и координаты по Х У
    public static final int WIN_WIDTH = 507;
    public static final int WIN_HEIGHT = 555;
    public static final int WIN_POSX = 650;
    public static final int WIN_POSY = 250;

    private Map map; //создаем переменную класса Map
    SettingsWindow settings;//Создаем переменную класса SettingsWindow

    //Конструктор класса
    GameWindow(){

        //Настройки окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  //Задает реакцию приложения на нажатие крестика в окне(при нажатии приложение останавливается)
        setSize(WIN_WIDTH, WIN_HEIGHT); //выводит размер
        //setLocation(WIN_POSX, WIN_POSY); //устанавливает позицию на экране
        setLocationRelativeTo(null);
        setTitle("TicTacToe"); //название окна
        setResizable(false); //возможность изменения размера окна: запрет

        //Кнопки
        JButton btnStart = new JButton("Start a new game");//Кнопка старта
        JButton btnExit = new JButton("<html><body><b>Exit</b></body></html>");// Кнопка выхода
        //создаем группу с раскладкой под другой компановщик(GridLayout)
        JPanel panelButton = new JPanel(new GridLayout(1,2));//Создает группу с сеточным расположением элементов в ней (1 строка, 2 колонки)
        //Вывод на экран
        panelButton.add(btnStart);//Добавили кнопку старта в группу panelButton
        panelButton.add(btnExit);//Добавили кнопку выхода в группу panelButton
        //отслеживает нажатие на кнопки
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(true);
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //Игровое поле
        map = new Map();//Создаем экземпляр класса Map
        settings = new SettingsWindow(this);//Создаем экземпляр класса SettingsWindow и связываем его с объектом GameWindow

        //Отображение
        add(panelButton, BorderLayout.SOUTH); //отмечаем где должна располагаться на экране группа с кнопками старт и выход
        add(map, BorderLayout.CENTER);//размещаем игровое поле

        setVisible(true); //сделать окно видимым: да
    }

    //метод транзит для передачи параметров настройки игры для класса Мар
    void applySettings(int mode, int size, int len){
        map.startNewGame(mode, size, size, len);
    }
}
