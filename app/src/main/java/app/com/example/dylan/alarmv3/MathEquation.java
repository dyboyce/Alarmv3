package app.com.example.dylan.alarmv3;

/**
 * Created by Ai Nakamura
 */

/**
 * Class MathEquation is used to generate a random equation to be
 * used by the Math game
 */
public class MathEquation {
    char ans;
    int num1;
    int num2;
    int total;

    /**
     * Constructor MathEquation
     */
    public MathEquation() {
        int a = (int) (Math.random() * 100);
        int b = (int) (Math.random() * 100);

        num1 = Math.max(a, b);
        num2 = Math.min(a, b);

        int switcher = ((int) (Math.random() * 100)) % 4;
        switch (switcher) {
            case 0:
                ans = '/';
                if (num1 % num2 == 0) {
                    total = num1 / num2;
                    break;
                }
                //else go to default
            case 1:
                ans = '*';
                if ((num1 * num2)<101) {
                    total = num1 * num2;
                    break;
                }
                //else go to default
            case 2:
                ans = '-';
                total = num1 - num2;
                break;
            default:
                ans = '+';
                total = num1 + num2;
                break;
        }
    }

    /**
     * method getNum1 accessor for num1.
     * @return
     */
    public int getNum1() {return num1;}

    /**
     * method getNum2 accessor for num2.
     * @return
     */
    public int getNum2() {return num2;}

    /**
     * method getAns accessor for ans.
     * @return
     */
    public char getAns() {return ans;}

    /**
     * method getTotal accessor for total.
     * @return
     */
    public int getTotal() {return total;}

    /**
     * method toString generate content for the textbox to display
     * the equation to be solved
     * @return
     */
    public String toString() {
        return "num1 is : " + num1 + "\n" +
                "num2 is : " + num2 + "\n" +
                "__________" + "\n" +
                "total is : " + total;
    }

}
