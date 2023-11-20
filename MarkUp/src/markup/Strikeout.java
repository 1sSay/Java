package markup;

import java.util.List;

public class Strikeout extends AbstractMarkElement {
    public Strikeout(List<AbstractTextElement> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        taggedToMarkDown(builder, "~", "~");
    }

    @Override 
    public void toBBCode(StringBuilder builder) {
        taggedToBBCode(builder, "[s]", "[/s]");
    }
}
