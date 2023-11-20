package markup;

import java.util.List;

public abstract class AbstractMarkElement extends AbstractTextElement {
    final private List<AbstractTextElement> elements;

    public AbstractMarkElement(List<AbstractTextElement> elements) {
        this.elements = elements;
    }

    protected void taggedToMarkDown(StringBuilder builder, String beginTag, String endTag) {
        builder.append(beginTag);

        for (AbstractTextElement elements : this.elements) {
            elements.toMarkdown(builder);
        }

        builder.append(endTag);
    }

    protected void taggedToBBCode(StringBuilder builder, String beginTag, String endTag) {
        builder.append(beginTag);

        for (AbstractTextElement elements : this.elements) {
            elements.toBBCode(builder);
        }

        builder.append(endTag);
    }
}
