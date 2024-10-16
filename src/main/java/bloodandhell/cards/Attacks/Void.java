package bloodandhell.cards.Attacks;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;

public class Void extends BaseCard {
    public static final String ID = makeID(Void.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int MN = 4;
    private static final int UPG_MN = -1;
    // ICI



    public Void() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(MN, UPG_MN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {  //Dans use, p = player et m = targeted ennemy. m =null si aucun ennemi n'est pointé)
        int nb_damage = (p.maxHealth - p.currentHealth) / this.magicNumber; // If my character has 80 HP max and is now at 60HP, you deal (80-60)/4 = 5. e.g : if you are at 10 HP : (80-10)/3 = 23
        addToBot(new DamageAction(m, new DamageInfo(p, nb_damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH)); //(1er paramètre = la cible à laquelle infliger des dégâts), DamageInfo (source des dégâts, amount et type de dégâts)
    }

    @Override
    public void applyPowers(){
        AbstractPlayer p = AbstractDungeon.player;
        int nb_damage = (p.maxHealth - p.currentHealth) / this.magicNumber;
        this.rawDescription = "Deal (\" + nb_damage + \") damage to an enemy. Damage equal your missing HP ÷ !M!. ";
        initializeDescription();

    }

    //Optional
    @Override
    public AbstractCard makeCopy() {
        return new Void();
    }
}
