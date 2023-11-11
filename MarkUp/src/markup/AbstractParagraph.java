package markup;

import java.util.List;

public abstract class AbstractParagraph implements ItemizableElement, MarkdownElement {
    List<AbstractTextElement> elements;

    public AbstractParagraph(List<AbstractTextElement> elements) {
        this.elements = elements;
    }
}
