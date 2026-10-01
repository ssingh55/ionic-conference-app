package io.ionic.conference.angular;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class SecureInputMethodService extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView keyboardView;
    private Keyboard keyboard;

    @Override
    public View onCreateInputView() {
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.secure_keyboard, null);
        keyboard = new Keyboard(this, R.xml.input_method_config);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    @Override
    public void onPress(int primaryCode) {
        // Handle key press without logging
    }

    @Override
    public void onRelease(int primaryCode) {
        // Handle key release without logging
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        if (ic != null) {
            if (primaryCode == Keyboard.KEYCODE_DELETE) {
                ic.deleteSurroundingText(1, 0);
            } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {
                toggleShift();
            } else if (primaryCode >= 0) {
                char c = (char) primaryCode;
                ic.commitText(String.valueOf(c), 1);
            }
        }
    }

    @Override
    public void onText(CharSequence text) {
        InputConnection ic = getCurrentInputConnection();
        if (ic != null) {
            ic.commitText(text, 1);
        }
    }

    @Override
    public void swipeLeft() {
        // Handle swipe without logging
    }

    @Override
    public void swipeRight() {
        // Handle swipe without logging
    }

    @Override
    public void swipeDown() {
        // Handle swipe without logging
    }

    @Override
    public void swipeUp() {
        // Handle swipe without logging
    }

    private void toggleShift() {
        if (keyboardView != null && keyboard != null) {
            boolean shiftState = !keyboard.isShifted();
            keyboard.setShifted(shiftState);
            keyboardView.invalidateAllKeys();
        }
    }
}
