import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RouletteGame extends JFrame {
    private final List<WheelSlot> wheelSlots = new ArrayList<>();
    private final SpinningWheelPanel spinningWheelPanel;
    private final Random random = new Random();

    public RouletteGame() {
        initializeWheelSlots();

        setTitle("Roulette Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        spinningWheelPanel = new SpinningWheelPanel(wheelSlots);
        add(spinningWheelPanel, BorderLayout.CENTER);

        JButton spinButton = new JButton("Spin Wheel");
        spinButton.addActionListener(e -> startSpin());
        add(spinButton, BorderLayout.SOUTH);
    }

    private void initializeWheelSlots() {
        for (int i = 0; i <= 36; i++) {
            Color color = (i == 0) ? Color.GREEN : (i % 2 == 0 ? Color.BLACK : Color.RED);
            wheelSlots.add(new WheelSlot(i, color));
        }
    }

    private void startSpin() {
        spinningWheelPanel.startSpinning();

        Timer timer = new Timer(3000, e -> {
    spinningWheelPanel.stopSpinning();
    WheelSlot result = spinningWheelPanel.getCurrentSlot();
    JOptionPane.showMessageDialog(null, "Result: " + result.getNumber() +
            " (" + result.getColor() + ")");
});
timer.setRepeats(false); // Run only once
timer.start();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RouletteGame game = new RouletteGame();
            game.setVisible(true);
        });
    }
}