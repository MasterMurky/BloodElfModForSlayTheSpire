package bloodandhell.powers;

import bloodandhell.cards.tempCards.SoldierDefend;
import bloodandhell.cards.tempCards.SoldierDevotion;
import bloodandhell.cards.tempCards.SoldierStrike;
import bloodandhell.cards.tempCards.NotASoldier;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import bloodandhell.util.GeneralUtils;
import bloodandhell.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommandingPresencePower extends AbstractPower {
    public static final String POWER_ID = "bloodandhell:CommandingPresencePower";
    private static final Random rand = new Random();

    public CommandingPresencePower(AbstractPlayer owner, int amount) {
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(this.ID);
        this.name = strings.NAME;
        this.description = strings.DESCRIPTIONS[0];

        // Chargement de l'image du pouvoir comme dans CollateralDamagePower
        String unPrefixed = GeneralUtils.removePrefix(POWER_ID);
        Texture normalTexture = TextureLoader.getPowerTexture(unPrefixed);
        Texture hiDefImage = TextureLoader.getHiDefPowerTexture(unPrefixed);

        // Charger l'image en fonction de l'existence des images
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null) {
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
            }
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        List<AbstractCard> cardsToAdd = new ArrayList<>();

        // Choisir les cartes à ajouter
        for (int i = 0; i < amount; i++) {
            AbstractCard card;
            int roll = rand.nextInt(50); // 1 chance out of 50 for a surprise :)

            if (roll == 0) {
                card = new NotASoldier();
            } else {
                // Sinon ajouter une carte parmi SoldierStrike, SoldierDefend, SoldierDevotion
                ArrayList<AbstractCard> pool = new ArrayList<>();
                pool.add(new SoldierStrike());
                pool.add(new SoldierDefend());
                pool.add(new SoldierDevotion());

                card = pool.get(rand.nextInt(pool.size())).makeCopy();
            }

            cardsToAdd.add(card);
        }

        // Ajouter les cartes à la main du joueur
        for (AbstractCard card : cardsToAdd) {
            AbstractDungeon.player.hand.addToTop(card);
        }

        // Ajouter un message d'info (optionnel, pour débogage)
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(owner, cardsToAdd.size()));
    }

}
