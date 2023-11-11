package markup;

import java.util.List;

public abstract class AbstractListElement implements ItemizableElement {
    List<ListItem> items;

    public AbstractListElement(List<ListItem> items) {
        this.items = items;
    }

    protected void taggedToBBCode(StringBuilder builder, String beginTag, String endTag) {
        builder.append(beginTag);
        for (ListItem item : this.items) {
            item.toBBCode(builder);
        }
        builder.append(endTag);
    }
}
