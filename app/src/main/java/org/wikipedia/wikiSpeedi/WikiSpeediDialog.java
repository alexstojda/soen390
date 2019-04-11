package org.wikipedia.wikiSpeedi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.support.annotation.NonNull;

import org.wikipedia.R;
import org.wikipedia.page.NoDimBottomSheetDialog;

public class WikiSpeediDialog extends NoDimBottomSheetDialog {

    private static boolean isRunning = false;
    private static int placeHolder = 0;
    private View sprintView = getLayoutInflater().inflate(R.layout.dialog_sprint_reader, null);
    private TextView sprintText = sprintView.findViewById(R.id.sprint_text);
    private String[] test = {"this", "is", "a", "super", "duper", "test", "that", "is", "fully", "functional.", "good", "job", "Siamak!"};
    private int delay = 200;
    private SeekBar seekBar;

    private Runnable set_sprintText = new Runnable() {
        public void run() {
            if (isRunning && placeHolder < test.length) {
                sprintText.setText(test[placeHolder]);
                placeHolder++;
                sprintText.postDelayed(this, delay);
            }
        }
    };

    public WikiSpeediDialog(@NonNull Context context, final String selectedText) {
        super(context);

        View rootView = LayoutInflater.from(context).inflate(R.layout.dialog_sprint_reader, null);
        setContentView(rootView);

        rootView.findViewById(R.id.close_button)
                .setOnClickListener((v) -> {
                    sprintText.removeCallbacks(set_sprintText);
                    resetSprint();
                    dismiss();
                });

        rootView.findViewById(R.id.start_sprint_button)
                .setOnClickListener((v) -> {

                    if (!isRunning) {
                        setIsRunning(true);
                        sprintText = findViewById(R.id.sprint_text);
                        sprintText.postDelayed(set_sprintText, 0);
                    }
                });

        rootView.findViewById(R.id.reset_sprint_button)
                .setOnClickListener((v) -> {
                    setIsRunning(false);
                    resetSprint();
                });

        rootView.findViewById(R.id.stop_sprint_button)
                .setOnClickListener((v) -> {
                    setIsRunning(false);
                });

        seekBar = rootView.findViewById(R.id.sprint_speed_bar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                delay = seekBar.getMax()-progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void resetSprint() {
        this.placeHolder = 0;
        this.isRunning = false;
        sprintText.setText("- start -");
    }

    public boolean getIsRunning() {
        return this.isRunning;
    }

    public int getPlaceHolder() {
        return this.placeHolder;
    }

    public String getSprintText() {
        return this.sprintText.getText().toString();
    }

    public SeekBar getSeekBar(){
        return seekBar;
    }

    public int getDelay() {
        return delay;
    }
}