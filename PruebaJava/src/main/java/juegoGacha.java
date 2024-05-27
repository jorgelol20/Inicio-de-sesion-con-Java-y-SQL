import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

public class juegoGacha {
    public static int main() {
        int[] dice = {0, 0, 0};
        Random random = new Random();
        dice[0] = random.nextInt(7);
        dice[1] = random.nextInt(7);
        dice[2] = random.nextInt(7);
        return greedy(dice);
        }

    public static int greedy(int[] dice) {
        int cont1 = 0;
        int cont2 = 0;
        int cont3 = 0;
        int cont4 = 0;
        int cont5 = 0;
        int cont6 = 0;
        int points = 0;
        for (int i = 0; i < dice.length; i++) {
            if (dice[i] == 1) {
                cont1++;
            }
            if (dice[i] == 2) {
                cont2++;
            }
            if (dice[i] == 3) {
                cont3++;
            }
            if (dice[i] == 4) {
                cont4++;
            }
            if (dice[i] == 5) {
                cont5++;
            }
            if (dice[i] == 6) {
                cont6++;
            }
        }
        do {
            if (cont1 >= 3) {
                points += 1000;
                cont1 -= 3;
            }
            if (cont2 >= 3) {
                points += 200;
                cont2 -= 3;
            }
            if (cont3 >= 3) {
                points += 300;
                cont3 -= 3;
            }
            if (cont4 >= 3) {
                points += 400;
                cont4 -= 3;
            }
            if (cont5 >= 3) {
                points += 500;
                cont5 -= 3;
            }
            if (cont6 >= 3) {
                points += 600;
                cont6 -= 3;
            }
        } while (cont1 >= 3 || cont2 >= 3 || cont3 >= 3 || cont4 >= 3 || cont5 >= 3 || cont6 >= 3);
        if (cont1 > 0) {
            points += 100 * cont1;
        }
        if (cont5 > 0) {
            points += 50 * cont5;
        }
        if (points>0){
            JOptionPane.showMessageDialog(null, "!FELICIDADES¡ Has ganado "+points+" puntos", "FELICIDADES", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "No has conseguido ningún punto", "Vaya...", JOptionPane.INFORMATION_MESSAGE);
        }
        return points;
    }
}
