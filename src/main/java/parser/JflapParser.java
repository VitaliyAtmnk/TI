package Parser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JflapConvertor {
    private final XmlMapper mapper;

    public JflapConvertor() {
        this.mapper = XmlMapper.builder()
                .defaultUseWrapper(false)
                .build();
    }

    public JflapModel readJflapFile(String fileName) {

    }


}
