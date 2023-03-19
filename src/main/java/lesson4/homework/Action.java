package lesson4.homework;

class Action {
    static int count = 0;
    private final int id;
    private final char action;
    private final double left;
    private final double right;
    private final double result;

    public Action(double result) {
        this('=', 0, 0, result);
    }

    public Action(char action, double left, double right, double result) {
        this.id = count++;
        this.action = action;
        this.left = left;
        this.right = right;
        this.result = result;
    }

    public void show() {
        if (this.action == '=') System.out.printf("%1.2f\n", this.result);
        else System.out.printf("%1.2f %c %1.2f = %1.2f\n", this.left, this.action, this.right, this.result);
    }

    public void showShort() {
        if (this.action == '=') System.out.printf("%1.2f\n", this.result);
        else System.out.printf("%c %1.2f\n", this.action, this.right);
    }

    public void showResult() {
        if (this.action == '=') System.out.printf("%1.2f\n", this.result);
        else System.out.printf("= %1.2f\n", this.result);
    }
}
