package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ShieldParticleEffect;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;

public class Purification extends BaseCard {
    public static final String ID = makeID(Purification.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    public Purification() {
        super(ID, info);
            }

    public Purification(String ID, CardStats info) {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Lumière sainte + bordures de l'écran qui brillent, comme prévu à l'origine.
        addToBot(new VFXAction(new ShieldParticleEffect(p.hb.cX, p.hb.cY)));
        addToBot(new VFXAction(new SpotlightPlayerEffect()));
        addToBot(new RemoveDebuffsAction(p));
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }

    //Optional
    @Override
    public AbstractCard makeCopy() {
        return new Purification();
    }
}
