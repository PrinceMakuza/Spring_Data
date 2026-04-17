package com.ecommerce;

import com.ecommerce.ui.FrontendApplication;

/**
 * Backward-compatible JavaFX launcher.
 * The actual frontend runtime now lives in {@link FrontendApplication}.
 */
public final class MainApp {

    private MainApp() {
    }

    public static void main(String[] args) {
        FrontendApplication.main(args);
    }
}
