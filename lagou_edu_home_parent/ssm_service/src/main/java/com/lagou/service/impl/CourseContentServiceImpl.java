package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: Emest
 * @date: 2021/4/22$ 19:26$
 * @description:
 */
@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper cm;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId) {
        return cm.findSectionAndLessonByCourseId(courseId);
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        return cm.findCourseByCourseId(courseId);
    }

    @Override
    public void saveSection(CourseSection section) {
        //补全信息
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);
        cm.saveSection(section);
    }

    @Override
    public void updateSection(CourseSection section) {
        //补全信息
        Date date = new Date();
        section.setUpdateTime(date);
        cm.updateSection(section);
    }

    @Override
    public void updateSectionStatus(int id, int status) {
        //封装数据
        CourseSection section = new CourseSection();
        section.setId(id);
        section.setStatus(status);
        section.setUpdateTime(new Date());
        cm.updateSectionStatus(section);
    }

    @Override
    public void saveLesson(CourseLesson lesson) {
        Date date = new Date();
        lesson.setCreateTime(date);
        lesson.setUpdateTime(date);
        //cm.saveLesson(lesson);
    }

    @Override
    public void updateLesson(CourseLesson lesson) {
        lesson.setUpdateTime(new Date());
        //cm.updateLesson(lesson);
    }
}
