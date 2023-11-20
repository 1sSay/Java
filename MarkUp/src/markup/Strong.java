package markup;

import java.util.List;

public class Strong extends AbstractMarkElement {
    public Strong(List<AbstractTextElement> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        taggedToMarkDown(builder, "__", "__");
    }

    @Override 
    public void toBBCode(StringBuilder builder) {
        taggedToBBCode(builder, "[b]", "[/b]");
    }
}
