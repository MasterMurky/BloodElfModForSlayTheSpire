package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Avarice extends BaseCard {
    public static final String ID = makeID(Avarice.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            -1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    // Montant soigné à chaque itération (X fois, X = énergie dépensée).
    private static final int MN = 2;
    private static final int UPG_MN = 1;

    public Avarice() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(MN, UPG_MN);
        setExhaust(true, false); // L'amélioration retire l'Exhaust (voir UPGRADE_DESCRIPTION).
    }

    public Avarice(String ID, CardStats info) {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        bloodandhell.BasicMod.logger.info("=== AVARICE DEBUG === cost=" + this.cost
                + " costForTurn=" + this.costForTurn
                + " energyOnUse=" + this.energyOnUse
                + " currentEnergy=" + com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.energy.energy);
        for (int i = 0; i < this.energyOnUse; i++) {
            addToBot(new LoseHPAction(p, p, 1));
            addToBot(new HealAction(p, p, this.magicNumber));
            addToBot(new GainEnergyAction(1));
        }
    }

    //Optional
    @Override
    public AbstractCard makeCopy() {
        return new Avarice();
    }
}
