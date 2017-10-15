package net.bgpat.elasticsearch.plugin.analysis.japanese;

import com.atilika.kuromoji.ipadic.neologd.Token;
import com.atilika.kuromoji.ipadic.neologd.Tokenizer;

import org.apache.commons.io.IOUtils;

import org.elasticsearch.index.analysis.AbstractCharFilterFactory;
import org.elasticsearch.index.analysis.MultiTermAwareComponent;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class KatakanaTransformCharFilterFactory extends AbstractCharFilterFactory implements MultiTermAwareComponent {
    public KatakanaTransformCharFilterFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name);
    }

    @Override
    public Reader create(Reader reader) {
        String src = null;
        try {
            src = IOUtils.toString(reader);
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
            return reader;
        }

        Tokenizer tokenizer = new Tokenizer() ;
        List<Token> tokens = tokenizer.tokenize(src);
        String kana = "";
        for (Token token : tokens) {
            if (token.isKnown()) {
                kana += token.getReading();
            } else {
                kana += token.getSurface();
            }
        }

        //Transliterator transliterator = Transliterator.getInstance("Any-Katakana");
        //kana = transliterator.transliterate(kana);

        return new StringReader(kana);
    }

    @Override
    public Object getMultiTermComponent() {
        return this;
    }
}
