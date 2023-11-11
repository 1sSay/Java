package markup;

import java.util.List;

public class UnorderedList extends AbstractListElement {
    public UnorderedList(List<ListItem> elements) {
        super(elements);
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        taggedToBBCode(builder, "[list]", "[/list]");
    }
}
