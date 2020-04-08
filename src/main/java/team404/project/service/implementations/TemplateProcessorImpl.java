package team404.project.service.implementations;

import freemarker.template.*;
import org.springframework.stereotype.Component;
import team404.project.service.interfaces.TemplateProcessor;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

@Component
public class TemplateProcessorImpl implements TemplateProcessor {
    Configuration cfg;

    public String processTemplate(String templateName, Map<String, Object> input) {
        try {
            Template template = cfg.getTemplate(templateName);
            StringWriter writer = new StringWriter();
            template.process(input, writer);
            writer.flush();
            return writer.toString();
        } catch (TemplateException | IOException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
    }

    public void setCfg(Configuration cfg) {
        this.cfg = cfg;
    }
}
