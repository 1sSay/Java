package markup;

import java.util.List;

public class ListItem implements BBCodeElement {
    List<ItemizableElement> elements;
    public ListItem(List<ItemizableElement> elements) {
        this.elements = elements;
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        builder.append("[*]");
        for (ItemizableElement element : this.elements) {
            element.toBBCode(builder);
        }
    }
}
