package org.wikipedia.wikispeedi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.wikipedia.R;
import org.wikipedia.page.NoDimBottomSheetDialog;

import java.util.List;

import java.util.Arrays;

public class WikiSpeediDialog extends NoDimBottomSheetDialog {

    private boolean isRunning = false;
    private int index = 0;
    private View sprintView = getLayoutInflater().inflate(R.layout.dialog_sprint_reader, null);
    private TextView sprintText = sprintView.findViewById(R.id.sprint_text);
    private int delay = 200;
    private SeekBar seekBar;
    private List<String> selectedText;

    private Runnable sprintTextRunnable = new Runnable() {
        public void run() {
            if (isRunning && index < selectedText.size()) {
                sprintText.setText(selectedText.get(index));
                index++;
                sprintText.postDelayed(this, 200);
            }
        }
    };

    public WikiSpeediDialog(@NonNull Context context, final String selectedText) {
        super(context);

        View rootView = LayoutInflater.from(context).inflate(R.layout.dialog_sprint_reader, null);
        setContentView(rootView);

        this.selectedText = Arrays.asList(selectedText.split("\\s"));

        rootView.findViewById(R.id.close_button)
                .setOnClickListener((v) -> {
                    sprintText.removeCallbacks(sprintTextRunnable);
                    resetSprint();
                    dismiss();
                });

        rootView.findViewById(R.id.start_sprint_button)
                .setOnClickListener((v) -> {

                    if (!isRunning) {
                        setIsRunning(true);
                        sprintText = findViewById(R.id.sprint_text);
                        sprintText.post(sprintTextRunnable);
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
                delay = seekBar.getMax() - progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    void resetSprint() {
        this.index = 0;
        this.isRunning = false;
        sprintText.setText(R.string.speed_reader_start);
    }

    boolean getIsRunning() {
        return this.isRunning;
    }

    public List<String> getSelectedText() {
        return selectedText;
    }

    int getIndex() {
        return this.index;
    }

    String getSprintText() {
        return this.sprintText.getText().toString();
    }

    @Override
    public void onStop() {
        super.onStop();
        resetSprint();
    }
}
