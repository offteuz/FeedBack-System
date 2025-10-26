package com.fiap.feedbacksystem.controller;

import com.fiap.feedbacksystem.model.dto.feedback.FeedbackRequestDTO;
import com.fiap.feedbacksystem.model.dto.feedback.FeedbackResponseDTO;
import com.fiap.feedbacksystem.service.FeedbackService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/criarFeedback")
    public FeedbackResponseDTO criarFeedback(@RequestBody FeedbackRequestDTO dto) {
        return feedbackService.create(dto);
    }
}
