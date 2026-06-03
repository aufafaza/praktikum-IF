public class SummonedMonster implements ISummoned {
    private Monster monster;
    private boolean isFaceUp;
    private boolean isAttacking;

    SummonedMonster(Monster m, boolean faceUp, boolean AttackPos) {
        this.monster = m;
        this.isFaceUp = faceUp;
        this.isAttacking = AttackPos;
    }

    @Override
    public boolean flip() {
        if (!this.isFaceUp) {
            isFaceUp = true;
            return true;
        }
        return false;
    }

    @Override
    public void rotate() {
        this.isAttacking = !this.isAttacking;
    }

    @Override
    public int getPositionValue() {
        if (isAttacking) {
            return monster.getAttackValue();
        } else {
            return monster.getDefenseValue();
        }
    }

    @Override
    public void render() {
        String msg = "Monster ";
        msg += this.monster.getName();
        msg += " dalam keadaan ";
        if (this.isFaceUp) {
            msg += "terbuka";
        } else {
            msg += "tertutup";
        }
        msg += " dengan posisi ";
        if (this.isAttacking) {
            msg += "menyerang";
        } else {
            msg += "bertahan";
        }
        System.out.println(msg);
    }

}
