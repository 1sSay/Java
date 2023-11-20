package markup;

import java.util.List;

public class Paragraph extends AbstractParagraph {

    public Paragraph(List<AbstractTextElement> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        for (AbstractTextElement element : this.elements) {
            element.toMarkdown(builder);
        }
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        for (AbstractTextElement element : this.elements) {
            element.toBBCode(builder);
        }
    }
}
