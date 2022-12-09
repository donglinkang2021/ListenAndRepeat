package com.example.myjavafxdemo2.listenerandrepeater;

import org.jnativehook.keyboard.NativeKeyEvent;

import java.awt.event.KeyEvent;

public class ChangeBtnType {
    // 将jnativehook的键盘和鼠标事件与robot的键盘和鼠标事件进行映射
    public static int mouseSpeakToRobot(int i) {
        // 将jnativehook的鼠标事件与robot的鼠标事件进行映射
        // 1: left, 2: right, 3: middle (但是在awt.robot中，1: left, 2: middle, 3: right)
        switch (i) {
            case 1:
                return KeyEvent.BUTTON1_DOWN_MASK;
            case 2:
                return KeyEvent.BUTTON3_DOWN_MASK;
            case 3:
                return KeyEvent.BUTTON2_DOWN_MASK;
            case 4:
                return 1<<14;
            case 5:
                return 1<<15;
            default:
                return 0;
        }
    }

    public static int keyboardSpeakToRobot(int keycode){
        // 将jnativehook的键盘事件与robot的键盘事件进行映射
        switch (keycode) {
            case NativeKeyEvent.VC_ESCAPE:
                return KeyEvent.VK_ESCAPE;
//            case NativeKeyEvent.VC_ADD:
//                return KeyEvent.VK_ADD;
//            case NativeKeyEvent.VC_SUBTRACT:
//                return KeyEvent.VK_SUBTRACT;
//            case NativeKeyEvent.VC_MULTIPLY:
//                return KeyEvent.VK_MULTIPLY;
//            case NativeKeyEvent.VC_DIVIDE:
//                return KeyEvent.VK_DIVIDE;
//            case NativeKeyEvent.VC_DECIMAL:
//                return KeyEvent.VK_DECIMAL;
//            case NativeKeyEvent.VC_NUMPAD0:
//                return KeyEvent.VK_NUMPAD0;
//            case NativeKeyEvent.VC_NUMPAD1:
//                return KeyEvent.VK_NUMPAD1;
//            case NativeKeyEvent.VC_NUMPAD2:
//                return KeyEvent.VK_NUMPAD2;
//            case NativeKeyEvent.VC_NUMPAD3:
//                return KeyEvent.VK_NUMPAD3;
//            case NativeKeyEvent.VC_NUMPAD4:
//                return KeyEvent.VK_NUMPAD4;
//            case NativeKeyEvent.VC_NUMPAD5:
//                return KeyEvent.VK_NUMPAD5;
//            case NativeKeyEvent.VC_NUMPAD6:
//                return KeyEvent.VK_NUMPAD6;
//            case NativeKeyEvent.VC_NUMPAD7:
//                return KeyEvent.VK_NUMPAD7;
//            case NativeKeyEvent.VC_NUMPAD8:
//                return KeyEvent.VK_NUMPAD8;
//            case NativeKeyEvent.VC_NUMPAD9:
//                return KeyEvent.VK_NUMPAD9;
            case NativeKeyEvent.VC_F1:
                return KeyEvent.VK_F1;
            case NativeKeyEvent.VC_F2:
                return KeyEvent.VK_F2;
            case NativeKeyEvent.VC_F3:
                return KeyEvent.VK_F3;
            case NativeKeyEvent.VC_F4:
                return KeyEvent.VK_F4;
            case NativeKeyEvent.VC_F5:
                return KeyEvent.VK_F5;
            case NativeKeyEvent.VC_F6:
                return KeyEvent.VK_F6;
            case NativeKeyEvent.VC_F7:
                return KeyEvent.VK_F7;
            case NativeKeyEvent.VC_F8:
                return KeyEvent.VK_F8;
            case NativeKeyEvent.VC_F9:
                return KeyEvent.VK_F9;
            case NativeKeyEvent.VC_F10:
                return KeyEvent.VK_F10;
            case NativeKeyEvent.VC_F11:
                return KeyEvent.VK_F11;
            case NativeKeyEvent.VC_F12:
                return KeyEvent.VK_F12;
            case NativeKeyEvent.VC_F13:
                return KeyEvent.VK_F13;
            case NativeKeyEvent.VC_F14:
                return KeyEvent.VK_F14;
            case NativeKeyEvent.VC_F15:
                return KeyEvent.VK_F15;
            case NativeKeyEvent.VC_F16:
                return KeyEvent.VK_F16;
            case NativeKeyEvent.VC_F17:
                return KeyEvent.VK_F17;
            case NativeKeyEvent.VC_F18:
                return KeyEvent.VK_F18;
            case NativeKeyEvent.VC_F19:
                return KeyEvent.VK_F19;
            case NativeKeyEvent.VC_F20:
                return KeyEvent.VK_F20;
            case NativeKeyEvent.VC_F21:
                return KeyEvent.VK_F21;
            case NativeKeyEvent.VC_F22:
                return KeyEvent.VK_F22;
            case NativeKeyEvent.VC_F23:
                return KeyEvent.VK_F23;
            case NativeKeyEvent.VC_F24:
                return KeyEvent.VK_F24;
            case NativeKeyEvent.VC_PRINTSCREEN:
                return KeyEvent.VK_PRINTSCREEN;
            case NativeKeyEvent.VC_SCROLL_LOCK:
                return KeyEvent.VK_SCROLL_LOCK;
            case NativeKeyEvent.VC_PAUSE:
                return KeyEvent.VK_PAUSE;
            case NativeKeyEvent.VC_INSERT:
                return KeyEvent.VK_INSERT;
            case NativeKeyEvent.VC_HOME:
                return KeyEvent.VK_HOME;
            case NativeKeyEvent.VC_PAGE_UP:
                return KeyEvent.VK_PAGE_UP;
            case NativeKeyEvent.VC_DELETE:
                return KeyEvent.VK_DELETE;
            case NativeKeyEvent.VC_END:
                return KeyEvent.VK_END;
            case NativeKeyEvent.VC_PAGE_DOWN:
                return KeyEvent.VK_PAGE_DOWN;
            case NativeKeyEvent.VC_UP:
                return KeyEvent.VK_UP;
            case NativeKeyEvent.VC_LEFT:
                return KeyEvent.VK_LEFT;
            case NativeKeyEvent.VC_CLEAR:
                return KeyEvent.VK_CLEAR;
            case NativeKeyEvent.VC_RIGHT:
                return KeyEvent.VK_RIGHT;
            case NativeKeyEvent.VC_DOWN:
                return KeyEvent.VK_DOWN;
            case NativeKeyEvent.VC_BACKQUOTE:
                return KeyEvent.VK_BACK_QUOTE;
            case NativeKeyEvent.VC_1:
                return KeyEvent.VK_1;
            case NativeKeyEvent.VC_2:
                return KeyEvent.VK_2;
            case NativeKeyEvent.VC_3:
                return KeyEvent.VK_3;
            case NativeKeyEvent.VC_4:
                return KeyEvent.VK_4;
            case NativeKeyEvent.VC_5:
                return KeyEvent.VK_5;
            case NativeKeyEvent.VC_6:
                return KeyEvent.VK_6;
            case NativeKeyEvent.VC_7:
                return KeyEvent.VK_7;
            case NativeKeyEvent.VC_8:
                return KeyEvent.VK_8;
            case NativeKeyEvent.VC_9:
                return KeyEvent.VK_9;
            case NativeKeyEvent.VC_0:
                return KeyEvent.VK_0;
            case NativeKeyEvent.VC_MINUS:
                return KeyEvent.VK_MINUS;
            case NativeKeyEvent.VC_EQUALS:
                return KeyEvent.VK_EQUALS;
            case NativeKeyEvent.VC_BACKSPACE:
                return KeyEvent.VK_BACK_SPACE;
            case NativeKeyEvent.VC_TAB:
                return KeyEvent.VK_TAB;
            case NativeKeyEvent.VC_CAPS_LOCK:
                return KeyEvent.VK_CAPS_LOCK;
            case NativeKeyEvent.VC_A:
                return KeyEvent.VK_A;
            case NativeKeyEvent.VC_B:
                return KeyEvent.VK_B;
            case NativeKeyEvent.VC_C:
                return KeyEvent.VK_C;
            case NativeKeyEvent.VC_D:
                return KeyEvent.VK_D;
            case NativeKeyEvent.VC_E:
                return KeyEvent.VK_E;
            case NativeKeyEvent.VC_F:
                return KeyEvent.VK_F;
            case NativeKeyEvent.VC_G:
                return KeyEvent.VK_G;
            case NativeKeyEvent.VC_H:
                return KeyEvent.VK_H;
            case NativeKeyEvent.VC_I:
                return KeyEvent.VK_I;
            case NativeKeyEvent.VC_J:
                return KeyEvent.VK_J;
            case NativeKeyEvent.VC_K:
                return KeyEvent.VK_K;
            case NativeKeyEvent.VC_L:
                return KeyEvent.VK_L;
            case NativeKeyEvent.VC_M:
                return KeyEvent.VK_M;
            case NativeKeyEvent.VC_N:
                return KeyEvent.VK_N;
            case NativeKeyEvent.VC_O:
                return KeyEvent.VK_O;
            case NativeKeyEvent.VC_P:
                return KeyEvent.VK_P;
            case NativeKeyEvent.VC_Q:
                return KeyEvent.VK_Q;
            case NativeKeyEvent.VC_R:
                return KeyEvent.VK_R;
            case NativeKeyEvent.VC_S:
                return KeyEvent.VK_S;
            case NativeKeyEvent.VC_T:
                return KeyEvent.VK_T;
            case NativeKeyEvent.VC_U:
                return KeyEvent.VK_U;
            case NativeKeyEvent.VC_V:
                return KeyEvent.VK_V;
            case NativeKeyEvent.VC_W:
                return KeyEvent.VK_W;
            case NativeKeyEvent.VC_X:
                return KeyEvent.VK_X;
            case NativeKeyEvent.VC_Y:
                return KeyEvent.VK_Y;
            case NativeKeyEvent.VC_Z:
                return KeyEvent.VK_Z;
            case NativeKeyEvent.VC_OPEN_BRACKET:
                return KeyEvent.VK_OPEN_BRACKET;
            case NativeKeyEvent.VC_CLOSE_BRACKET:
                return KeyEvent.VK_CLOSE_BRACKET;
            case NativeKeyEvent.VC_BACK_SLASH:
                return KeyEvent.VK_BACK_SLASH;
            case NativeKeyEvent.VC_SEMICOLON:
                return KeyEvent.VK_SEMICOLON;
            case NativeKeyEvent.VC_QUOTE:
                return KeyEvent.VK_QUOTE;
            case NativeKeyEvent.VC_ENTER:
                return KeyEvent.VK_ENTER;
            case NativeKeyEvent.VC_COMMA:
                return KeyEvent.VK_COMMA;
            case NativeKeyEvent.VC_PERIOD:
                return KeyEvent.VK_PERIOD;
            case NativeKeyEvent.VC_SLASH:
                return KeyEvent.VK_SLASH;
            case NativeKeyEvent.VC_SPACE:
                return KeyEvent.VK_SPACE;
            case NativeKeyEvent.VC_NUM_LOCK:
                return KeyEvent.VK_NUM_LOCK;
            case NativeKeyEvent.VC_SEPARATOR:
                return KeyEvent.VK_SEPARATOR;
            case NativeKeyEvent.VC_SHIFT:
                return KeyEvent.VK_SHIFT;
            case NativeKeyEvent.VC_CONTROL:
                return KeyEvent.VK_CONTROL;
            case NativeKeyEvent.VC_ALT:
                return KeyEvent.VK_ALT;
            case NativeKeyEvent.VC_META:
                return KeyEvent.VK_META;
            case NativeKeyEvent.VC_CONTEXT_MENU:
                return KeyEvent.VK_CONTEXT_MENU;
//            case NativeKeyEvent.VC_POWER:
//                return KeyEvent.VK_POWER;
//            case NativeKeyEvent.VC_SLEEP:
//                return KeyEvent.VK_SLEEP;
//            case NativeKeyEvent.VC_FINAL:
//                return KeyEvent.VK_FINAL;
//            case NativeKeyEvent.VC_CONVERT:
//                return KeyEvent.VK_CONVERT;
//            case NativeKeyEvent.VC_NONCONVERT:
//                return KeyEvent.VK_NONCONVERT;
//            case NativeKeyEvent.VC_ACCEPT:
//                return KeyEvent.VK_ACCEPT;
//            case NativeKeyEvent.VC_MODECHANGE:
//                return KeyEvent.VK_MODECHANGE;
//            case NativeKeyEvent.VC_KANA:
//                return KeyEvent.VK_KANA;
            case NativeKeyEvent.VC_KANJI:
                return KeyEvent.VK_KANJI;
//            case NativeKeyEvent.VC_ALPHANUMERIC:
//                return KeyEvent.VK_ALPHANUMERIC;
            case NativeKeyEvent.VC_KATAKANA:
                return KeyEvent.VK_KATAKANA;
            case NativeKeyEvent.VC_HIRAGANA:
                return KeyEvent.VK_HIRAGANA;
//            case NativeKeyEvent.VC_FULL_WIDTH:
//                return KeyEvent.VK_FULL_WIDTH;
//            case NativeKeyEvent.VC_HALF_WIDTH:
//                return KeyEvent.VK_HALF_WIDTH;
//            case NativeKeyEvent.VC_ROMAN_CHARACTERS:
//                return KeyEvent.VK_ROMAN_CHARACTERS;
//            case NativeKeyEvent.VC_ALL_CANDIDATES:
//                return KeyEvent.VK_ALL_CANDIDATES;
//            case NativeKeyEvent.VC_PREVIOUS_CANDIDATE:
//                return KeyEvent.VK_PREVIOUS_CANDIDATE;
//            case NativeKeyEvent.VC_CODE_INPUT:
//                return KeyEvent.VK_CODE_INPUT;
//            case NativeKeyEvent.VC_JAPANESE_KATAKANA:
//                return KeyEvent.VK_JAPANESE_KATAKANA;
//            case NativeKeyEvent.VC_JAPANESE_HIRAGANA:
//                return KeyEvent.VK_JAPANESE_HIRAGANA;
//            case NativeKeyEvent.VC_JAPANESE_ROMAN:
//                return KeyEvent.VK_JAPANESE_ROMAN;
//            case NativeKeyEvent.VC_KANA_LOCK:
//                return KeyEvent.VK_KANA_LOCK;
//            case NativeKeyEvent.VC_INPUT_METHOD_ON_OFF:
//                return KeyEvent.VK_INPUT_METHOD_ON_OFF;
//            case NativeKeyEvent.VC_CUT:
//                return KeyEvent.VK_CUT;
//            case NativeKeyEvent.VC_COPY:
//                return KeyEvent.VK_COPY;
//            case NativeKeyEvent.VC_PASTE:
//                return KeyEvent.VK_PASTE;
//            case NativeKeyEvent.VC_UNDO:
//                return KeyEvent.VK_UNDO;
//            case NativeKeyEvent.VC_AGAIN:
//                return KeyEvent.VK_AGAIN;
//            case NativeKeyEvent.VC_FIND:
//                return KeyEvent.VK_FIND;
//            case NativeKeyEvent.VC_PROPS:
//                return KeyEvent.VK_PROPS;
//            case NativeKeyEvent.VC_STOP:
//                return KeyEvent.VK_STOP;
//            case NativeKeyEvent.VC_COMPOSE:
//                return KeyEvent.VK_COMPOSE;
//            case NativeKeyEvent.VC_ALT_GRAPH:
//                return KeyEvent.VK_ALT_GRAPH;
//            case NativeKeyEvent.VC_BEGIN:
//                return KeyEvent.VK_BEGIN;
            default:
                return KeyEvent.VK_UNDEFINED;
        }
    }

}
