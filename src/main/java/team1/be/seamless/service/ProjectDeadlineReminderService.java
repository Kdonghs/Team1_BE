package team1.be.seamless.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import team1.be.seamless.entity.ProjectEntity;
import team1.be.seamless.repository.ProjectRepository;
import team1.be.seamless.util.MailSend;

@Service
public class ProjectDeadlineReminderService {

    private final ProjectRepository projectRepository;
    //    private final EmailSend emailSend;
    private final MailSend mailSend;

//    @Autowired
//    public ProjectDeadlineReminderService(ProjectRepository projectRepository, EmailSend emailSend) {
//        this.projectRepository = projectRepository;
//        this.emailSend = emailSend;
//    }


    @Autowired
    public ProjectDeadlineReminderService(ProjectRepository projectRepository, MailSend mailSend) {
        this.projectRepository = projectRepository;
        this.mailSend = mailSend;
    }

    // 오후 4시에 이메일 전송함
    @Scheduled(cron = "0 0 16 * * ?")
    public void sendDeadlineReminders() {
        List<ProjectEntity> projects = projectRepository.findAllByIsDeletedFalse();

        for (ProjectEntity project : projects) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime endDate = project.getEndDate();
            long daysUntilDeadline = ChronoUnit.DAYS.between(now, endDate);

            // 마감일이 14일, 7일, 3일, 1일 남았을 때 이메일 전송
            if (daysUntilDeadline == 14 || daysUntilDeadline == 7 || daysUntilDeadline == 3
                || daysUntilDeadline == 1) {
                String subject = "[프로젝트 마감 임박 알림] '" + project.getName() + "' 프로젝트";
                String message = """
                    프로젝트 %s의 마감 기한이 %d
                    
                    프로젝트 관련 작업을 마무리해 주세요.
                    
                    감사합니다.""".formatted(project.getName(), daysUntilDeadline);

                project.getMemberEntities().forEach(member ->
                    mailSend.send(member.getEmail(), subject, message));
            }
        }
    }
}
