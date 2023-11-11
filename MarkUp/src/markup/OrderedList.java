package markup;

import java.util.List;

public class OrderedList extends AbstractListElement {
    public OrderedList(List<ListItem> elements) {
        super(elements);
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        taggedToBBCode(builder, "[list=1]", "[/list]");
    }
}
