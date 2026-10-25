package com.vinayaalla.portfolio.api;

import com.vinayaalla.portfolio.project.ProjectSummary;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PortfolioController {
    private static final String REPOSITORY_URL = "https://github.com/Vinayaalla";
    private static final List<ProjectSummary> PROJECTS = List.of(
            new ProjectSummary(
                    "agriculture",
                    "Intelligent Distribution of Fresh Agricultural Products in Smart Cities",
                    "A smart supply chain platform that tracks agricultural products from farms to consumers using QR codes. Users can scan products to view origin, transportation details, freshness information, and product history.",
                    "🌾",
                    List.of("HTML", "CSS", "JavaScript", "Java", "Spring Boot", "MySQL"),
                    REPOSITORY_URL,
                    "agriculture-project-details"),
            new ProjectSummary(
                    "hashtags",
                    "Filtering Instagram Hashtags Through Crowd Tagging and HITS Algorithm",
                    "A system that improves hashtag relevance by analyzing uploaded images, collecting crowd-generated tags, and ranking hashtags using the HITS algorithm to improve search accuracy and content discoverability.",
                    "📸",
                    List.of("HTML", "CSS", "JavaScript", "Java", "Spring Boot", "MySQL"),
                    REPOSITORY_URL,
                    "hashtag-project-details"),
            new ProjectSummary(
                    "job-portal",
                    "Job Portal and Recruitment Management System",
                    "A web application that connects recruiters and job seekers. Recruiters can post jobs and manage applications while candidates can search jobs, upload resumes, and track application status.",
                    "💼",
                    List.of("HTML", "CSS", "JavaScript", "React", "Java", "Spring Boot", "MySQL"),
                    REPOSITORY_URL,
                    "job-portal-project-details"));

    @GetMapping("/projects")
    public List<ProjectSummary> getProjects() {
        return PROJECTS;
    }

    @GetMapping("/health")
    public HealthResponse health() {
        return new HealthResponse("ok");
    }

    public record HealthResponse(String status) {
    }
}