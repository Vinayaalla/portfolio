package com.vinayaalla.portfolio.project;

import java.util.List;

public record ProjectSummary(
        String id,
        String title,
        String description,
        String icon,
        List<String> technologies,
        String repositoryUrl,
        String detailsTemplateId) {
}