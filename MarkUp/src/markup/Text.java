package markup;

public class Text extends AbstractTextElement {
    final private String value;

    public Text(String value) {
        this.value = value;
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        builder.append(this.value);
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        builder.append(this.value);
    }
}
