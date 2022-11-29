package com.example.quizzapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level3 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft; //Переменная для левой картинки + текст
    public int numRight; //Переменная для правой картинки + текст
    Array array = new Array(); //Создали новый объект из класса Array
    Random random = new Random();
    public int count = 0; //Счетчик правильных ответов

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //Создаем переменную text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.Level3);

        final ImageView img_left = findViewById(R.id.img_left);
        //код, который скругляет углы левой картинки
        img_left.setClipToOutline(true);

        final ImageView img_right = findViewById(R.id.img_right);
        //код, который скругляет углы правой картинки
        img_right.setClipToOutline(true);

        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);

        //Развернуть игру на весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //Развернуть игру на весь экран - конец

        //Устанавливаем фон диалогового окна
        ImageView background = (ImageView)findViewById(R.id.background);
        background.setImageResource(R.drawable.level3);

        //Вызов диалогового окна в начале игры
        dialog = new Dialog(this); //Создаем новое диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //Скрываем диалоговое окно
        dialog.setContentView(R.layout.previewdialog); //Путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон окна
        dialog.setCancelable(false); //окно нельзя закрыть кнопкой "Назад"
        //Устанавливаем картинку в диалоговое окно
        ImageView previewimg = (ImageView)dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimg3);

        //Устанавливаем фон диалогового окна
        LinearLayout dialogfon = (LinearLayout)dialog.findViewById(R.id.dialogfon);
        dialogfon.setBackgroundResource(R.drawable.previewbackground3);

        //Устанавливаем описание задания
        TextView textdescription = (TextView)dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.levelthree);

        //Кнопка, которое закрывает диалоговое окно
        TextView btnclose = dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    //Вернуться к выбору уровня
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                dialog.dismiss(); //Зыкрываем диалоговое окно
            }
        });

        //Кнопка "продолжить"
        Button btncontinue = dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show(); //показать диалоговое окно

        //______________________________________________
        //Вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this); //Создаем новое диалоговое окно
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); //Скрываем диалоговое окно
        dialogEnd.setContentView(R.layout.dialogend); //Путь к макету диалогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false); //окно нельзя закрыть кнопкой "Назад"

        //Устанавливаем фон диалогового окна
        LinearLayout dialogfonEnd = (LinearLayout)dialogEnd.findViewById(R.id.dialogfon);
        dialogfonEnd.setBackgroundResource(R.drawable.previewbackground3);

        //Интересный факт
        TextView textdescriptionEnd = (TextView)dialogEnd.findViewById(R.id.textdescriptionend);
        textdescriptionEnd.setText(R.string.levelthreeEnd);


        //Кнопка, которое закрывает диалоговое окно
        TextView btnclose2 = dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    //Вернуться к выбору уровня
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                dialogEnd.dismiss(); //Зыкрываем диалоговое окно
            }
        });

        //Кнопка "продолжить"
        Button btncontinue2 = dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(Level3.this, Level4.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){}

                dialogEnd.dismiss();
            }
        });
        //______________________________________________

        //Кнопка "назад"
        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setBackgroundResource(R.drawable.button_stroke_black95_press_white);
//        btn_back.setTextColor(R.color.black95);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e){

                }
            }
        });

        //Массив для прогресса игры
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };

        //Подключаем анимацию
        final Animation a = AnimationUtils.loadAnimation(Level3.this, R.anim.alpha);


        //Работа с массивом для уровня
        numLeft = random.nextInt(21); //Генерируем случайное число
        img_left.setImageResource(array.images3[numLeft]); //Достаем из массива картинку
        text_left.setText(array.texts3[numLeft]); //Достаем текст

        numRight = random.nextInt(21);

        //Цикл с предусловием, проверяющий равенство чисел
        while (numLeft==numRight){
            numRight = random.nextInt(21);
        }

        img_right.setImageResource(array.images3[numRight]);
        text_right.setText(array.texts3[numRight]);

        //Обработка нажатия на левую картинку
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Условие касания картинки
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    //Если коснулся картинки
                    img_right.setEnabled(false); //Блокируем правую картинку
                    if (numLeft > numRight){
                        img_left.setImageResource(R.drawable.img_true);
                    }else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    //Если отпустил палец
                    if(numLeft > numRight){
                        //Если левая картинка больше
                        if(count < 20){
                            count = count + 1;
                        }
                        //Закрашиваем прогресс серым цветом
                        for (int i = 0; i < 20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else {
                        //Если левая картинка меньше
                        if (count > 0){
                            if(count == 1){
                                count = 0;
                            }else {
                                count = count - 2;
                            }
                        }
                        //Закрашиваем прогресс серым цветом
                        for (int i = 0; i < 19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                        if (count == 20){
                            //Выход из уровня
                            dialogEnd.show();
                        }else{
                            numLeft = random.nextInt(21); //Генерируем случайное число
                            img_left.setImageResource(array.images3[numLeft]); //Достаем из массива картинку
                            img_left.startAnimation(a);
                            text_left.setText(array.texts3[numLeft]); //Достаем текст

                            numRight = random.nextInt(21);

                            //Цикл с предусловием, проверяющий равенство чисел
                            while (numLeft==numRight){
                                numRight = random.nextInt(21);
                            }

                            img_right.setImageResource(array.images3[numRight]);
                            img_right.startAnimation(a);
                            text_right.setText(array.texts3[numRight]);
                            img_right.setEnabled(true); //Включаем обратно правую картинку
                        }
                }
                return true;
            }
        });
        //Обработка нажатия на правую картинку
        //Обработка нажатия на левую картинку
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Условие касания картинки
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    //Если коснулся картинки
                    img_left.setEnabled(false); //Блокируем левую картинку
                    if (numLeft < numRight){
                        img_right.setImageResource(R.drawable.img_true);
                    }else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    //Если отпустил палец
                    if(numLeft < numRight){
                        //Если правая картинка больше
                        if(count < 20){
                            count = count + 1;
                        }
                        //Закрашиваем прогресс серым цветом
                        for (int i = 0; i < 20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else {
                        //Если правая картинка меньше
                        if (count > 0){
                            if(count == 1){
                                count = 0;
                            }else {
                                count = count - 2;
                            }
                        }
                        //Закрашиваем прогресс серым цветом
                        for (int i = 0; i < 19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count == 20){
                        //Выход из уровня
                        dialogEnd.show();
                    }else{
                        numLeft = random.nextInt(21); //Генерируем случайное число
                        img_left.setImageResource(array.images3[numLeft]); //Достаем из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.texts3[numLeft]); //Достаем текст

                        numRight = random.nextInt(21);

                        //Цикл с предусловием, проверяющий равенство чисел
                        while (numLeft==numRight){
                            numRight = random.nextInt(21);
                        }

                        img_right.setImageResource(array.images3[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts3[numRight]);

                        img_left.setEnabled(true); //Включаем обратно левую картинку
                    }
                }
                return true;
            }
        });
    }
    //Системная кнопка "Назад"
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Level3.this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e){

        }
    }
}