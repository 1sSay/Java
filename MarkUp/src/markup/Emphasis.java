package markup;

import java.util.List;

public class Emphasis extends AbstractMarkElement {
    public Emphasis(List<AbstractTextElement> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        taggedToMarkDown(builder, "*", "*");
    }

    @Override 
    public void toBBCode(StringBuilder builder) {
        taggedToBBCode(builder, "[i]", "[/i]");
    }
}
