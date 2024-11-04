import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SpinningWheelPanel extends JPanel {
    private final List<WheelSlot> slots;
    private int currentSlotIndex = 0;
    private final Timer spinTimer;

    public SpinningWheelPanel(List<WheelSlot> slots) {
        this.slots = slots;

        spinTimer = new Timer(100, e -> {
            currentSlotIndex = (currentSlotIndex + 1) % slots.size();
            repaint();
        });
    }

    public void startSpinning() {
        spinTimer.start();
    }

    public void stopSpinning() {
        spinTimer.stop();
    }

    public WheelSlot getCurrentSlot() {
        return slots.get(currentSlotIndex);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        WheelSlot slot = getCurrentSlot();
        g.drawString("Slot: " + slot.getNumber() + " (" + slot.getColor() + ")", 50, 50);
    }
}
