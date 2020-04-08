package team404.project.service.interfaces;

import java.util.Map;

public interface TemplateProcessor {
    String processTemplate(String template, Map<String, Object> parameters);
}
