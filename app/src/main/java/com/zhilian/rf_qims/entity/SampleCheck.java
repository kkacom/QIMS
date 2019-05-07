package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/7.
 */

//样品检测数据
@Entity
public class SampleCheck implements Serializable {
    private static final long serialVersionUID = -61350503554458077L;
    @Id(autoincrement = true)
    private Long id;//样品ID
    private int status;//17.状态（0.服务器委托，1.本地新建，2.已上传）
    private long pid;//18.委托单Id
    //生产尺寸-门框(底框)
    private String door_frame_height1;
    private String door_frame_width1;
    private String door_frame_diagonal;
    private String door_frame_diagonal_bottom_fulcrum;
    private String door_frame_diagonal_bottom_fulcrum_difference;
    private String door_frame_diagonal_top_fulcrum;
    private String door_frame_diagonal_top_fulcrum_difference;

    //生产尺寸-门扇(底扇)
    private String door_leaf_height1;
    private String door_leaf_width1;
    private String door_leaf_diagonal;
    private String door_leaf_thickess;
    private String embedded_plate_height1;

    //生产尺寸-悬摆门
    private String hanging_plate_height1;
    private String hanging_plate_height_center1;
    private String hanging_plate_height_bottom1;
    private String hanging_plate_width1;
    private String hanging_plate_width_center1;
    private String hanging_plate_width_bottom1;
    private String swing_plate_diagonal1;
    private String swing_plate_diagonal2;



    //装配尺寸-直径公差
    private String hingepage_shaft_diameter1;
    private String hingepage_hole_diameter1;
    private String atresia_shaft_diameter1;
    private String atresia_hole_diameter1;


    //装配尺寸-平面度
    private String door_frame_surface1;
    private String door_leaf_surface1;
    private String swing_plate_flatness1;



    //生产质量-钢板厚度
    private String door_frame_left1;
    private String door_frame_right1;
    private String door_leaf_positive1;
    private String door_leaf_opposite1;

    //生产质量-漆膜质量
    private String film_thickness11;
    private String film_thickness21;
    private String film_thickness31;
    private String film_thickness41;
    private String film_adhesion11;
    private String film_adhesion21;
    private String film_adhesion31;
    private String film_adhesion41;

    //生产质量-加工质量
    private String leg_height_vertical1;
    private String leg_height_across1;

    //生产质量-混凝土强度
    private String carbonation_depth1;
    private String carbonation_depth2;
    private String carbonation_depth3;
    private String carbonation_depth4;
    private String carbonation_depth5;
    private String carbonation_depth6;
    private String carbonation_depth7;
    private String carbonation_depth8;
    private String carbonation_depth9;
    private String carbonation_depth10;
    private String carbonation_depth;
    private String carbonation_depth_avg;
    private String springback_prediction;
    private String crebound_file_name;//写错了
    private String rebound_file_name;
    //生产质量-钢筋分布
    private String protective_layer_thickness;
    private String steelbar_spacing;
    private String bar_spacing_file_name;

    //生产质量-悬摆门
    private String door_leaf_base_vent_width1;
    private String door_leaf_base_vent_height1;
    private String hanging_plate_thickness_top1;
    private String hanging_plate_thickness_bottom1;
    private String inside_plate_thickness1;


    //安装质量-贴合面
    private String surface_gap1;
    private String lm_fillister_left_top;
    private String lm_fillister_left_center;
    private String lm_fillister_left_bottom;
    private String lm_fillister_right_top;
    private String lm_fillister_right_center;
    private String lm_fillister_right_bottom;
    private String lm_indentation_left_top;
    private String lm_indentation_left_center;
    private String lm_indentation_left_bottom;
    private String lm_indentation_right_top;
    private String lm_indentation_right_center;
    private String lm_indentation_right_bottom;
    private String panel_thickness;
    private String blm_fillister_left_top;
    private String blm_fillister_left_center;
    private String blm_fillister_left_bottomp;//写错了
    private String blm_fillister_left_bottom;
    private String blm_fillister_right_top;
    private String blm_fillister_right_center;
    private String blm_fillister_right_bottom;


    //安装质量-垂直度
    private String hinge_axis_perpendicularity_bothway;
    private String hinge_axis_perpendicularity_vertical;
    private String hinge_axis_perpendicularity_parallel;
    private String frame_inter_wall_verticality1;
    private String frame_inter_wall_verticality2;
    private String frame_inter_wall_verticality3;
    private String frame_inter_wall_verticality4;
    private String line_hammer_specifications;

    //安装质量-使用性能
    private String door_open_force1;
    private String door_close_force1;
    private String lock_control_force1;
    private String lock_control_forceNM1;
    private String handleLength;
    private String performance_of_door_fans_left;
    private String door_fan_ambient_noise_left;
    private String door_leaf_noise_left;
    private String performance_of_door_fans_right;
    private String door_fan_ambient_noise_right ;
    private String door_leaf_noise_right ;

    //安装质量-悬摆门
    private String close_dangling_board_starting_force_top1;
    private String swing_plate_quality;
    private String swing_plate_hingeseat_quality;
    private String swing_plate_closing_the_most_strongly_top1;
    private String swing_plate_biggest_gap_between_door_leaf_top1;
    private String door_frame_leaf_max_clearance1;


    public String getRebound_file_name() {
        return rebound_file_name;
    }

    public void setRebound_file_name(String rebound_file_name) {
        this.rebound_file_name = rebound_file_name;
    }

    public String getBlm_fillister_left_bottom() {
        return blm_fillister_left_bottom;
    }

    public void setBlm_fillister_left_bottom(String blm_fillister_left_bottom) {
        this.blm_fillister_left_bottom = blm_fillister_left_bottom;
    }

    @Generated(hash = 1086200704)
    public SampleCheck(Long id, int status, long pid, String door_frame_height1, String door_frame_width1,
            String door_frame_diagonal, String door_frame_diagonal_bottom_fulcrum,
            String door_frame_diagonal_bottom_fulcrum_difference, String door_frame_diagonal_top_fulcrum,
            String door_frame_diagonal_top_fulcrum_difference, String door_leaf_height1, String door_leaf_width1,
            String door_leaf_diagonal, String door_leaf_thickess, String embedded_plate_height1,
            String hanging_plate_height1, String hanging_plate_height_center1, String hanging_plate_height_bottom1,
            String hanging_plate_width1, String hanging_plate_width_center1, String hanging_plate_width_bottom1,
            String swing_plate_diagonal1, String swing_plate_diagonal2, String hingepage_shaft_diameter1,
            String hingepage_hole_diameter1, String atresia_shaft_diameter1, String atresia_hole_diameter1,
            String door_frame_surface1, String door_leaf_surface1, String swing_plate_flatness1, String door_frame_left1,
            String door_frame_right1, String door_leaf_positive1, String door_leaf_opposite1, String film_thickness11,
            String film_thickness21, String film_thickness31, String film_thickness41, String film_adhesion11,
            String film_adhesion21, String film_adhesion31, String film_adhesion41, String leg_height_vertical1,
            String leg_height_across1, String carbonation_depth1, String carbonation_depth2, String carbonation_depth3,
            String carbonation_depth4, String carbonation_depth5, String carbonation_depth6, String carbonation_depth7,
            String carbonation_depth8, String carbonation_depth9, String carbonation_depth10, String carbonation_depth,
            String carbonation_depth_avg, String springback_prediction, String crebound_file_name, String rebound_file_name,
            String protective_layer_thickness, String steelbar_spacing, String bar_spacing_file_name,
            String door_leaf_base_vent_width1, String door_leaf_base_vent_height1, String hanging_plate_thickness_top1,
            String hanging_plate_thickness_bottom1, String inside_plate_thickness1, String surface_gap1,
            String lm_fillister_left_top, String lm_fillister_left_center, String lm_fillister_left_bottom,
            String lm_fillister_right_top, String lm_fillister_right_center, String lm_fillister_right_bottom,
            String lm_indentation_left_top, String lm_indentation_left_center, String lm_indentation_left_bottom,
            String lm_indentation_right_top, String lm_indentation_right_center, String lm_indentation_right_bottom,
            String panel_thickness, String blm_fillister_left_top, String blm_fillister_left_center,
            String blm_fillister_left_bottomp, String blm_fillister_left_bottom, String blm_fillister_right_top,
            String blm_fillister_right_center, String blm_fillister_right_bottom,
            String hinge_axis_perpendicularity_bothway, String hinge_axis_perpendicularity_vertical,
            String hinge_axis_perpendicularity_parallel, String frame_inter_wall_verticality1,
            String frame_inter_wall_verticality2, String frame_inter_wall_verticality3,
            String frame_inter_wall_verticality4, String line_hammer_specifications, String door_open_force1,
            String door_close_force1, String lock_control_force1, String lock_control_forceNM1, String handleLength,
            String performance_of_door_fans_left, String door_fan_ambient_noise_left, String door_leaf_noise_left,
            String performance_of_door_fans_right, String door_fan_ambient_noise_right, String door_leaf_noise_right,
            String close_dangling_board_starting_force_top1, String swing_plate_quality,
            String swing_plate_hingeseat_quality, String swing_plate_closing_the_most_strongly_top1,
            String swing_plate_biggest_gap_between_door_leaf_top1, String door_frame_leaf_max_clearance1) {
        this.id = id;
        this.status = status;
        this.pid = pid;
        this.door_frame_height1 = door_frame_height1;
        this.door_frame_width1 = door_frame_width1;
        this.door_frame_diagonal = door_frame_diagonal;
        this.door_frame_diagonal_bottom_fulcrum = door_frame_diagonal_bottom_fulcrum;
        this.door_frame_diagonal_bottom_fulcrum_difference = door_frame_diagonal_bottom_fulcrum_difference;
        this.door_frame_diagonal_top_fulcrum = door_frame_diagonal_top_fulcrum;
        this.door_frame_diagonal_top_fulcrum_difference = door_frame_diagonal_top_fulcrum_difference;
        this.door_leaf_height1 = door_leaf_height1;
        this.door_leaf_width1 = door_leaf_width1;
        this.door_leaf_diagonal = door_leaf_diagonal;
        this.door_leaf_thickess = door_leaf_thickess;
        this.embedded_plate_height1 = embedded_plate_height1;
        this.hanging_plate_height1 = hanging_plate_height1;
        this.hanging_plate_height_center1 = hanging_plate_height_center1;
        this.hanging_plate_height_bottom1 = hanging_plate_height_bottom1;
        this.hanging_plate_width1 = hanging_plate_width1;
        this.hanging_plate_width_center1 = hanging_plate_width_center1;
        this.hanging_plate_width_bottom1 = hanging_plate_width_bottom1;
        this.swing_plate_diagonal1 = swing_plate_diagonal1;
        this.swing_plate_diagonal2 = swing_plate_diagonal2;
        this.hingepage_shaft_diameter1 = hingepage_shaft_diameter1;
        this.hingepage_hole_diameter1 = hingepage_hole_diameter1;
        this.atresia_shaft_diameter1 = atresia_shaft_diameter1;
        this.atresia_hole_diameter1 = atresia_hole_diameter1;
        this.door_frame_surface1 = door_frame_surface1;
        this.door_leaf_surface1 = door_leaf_surface1;
        this.swing_plate_flatness1 = swing_plate_flatness1;
        this.door_frame_left1 = door_frame_left1;
        this.door_frame_right1 = door_frame_right1;
        this.door_leaf_positive1 = door_leaf_positive1;
        this.door_leaf_opposite1 = door_leaf_opposite1;
        this.film_thickness11 = film_thickness11;
        this.film_thickness21 = film_thickness21;
        this.film_thickness31 = film_thickness31;
        this.film_thickness41 = film_thickness41;
        this.film_adhesion11 = film_adhesion11;
        this.film_adhesion21 = film_adhesion21;
        this.film_adhesion31 = film_adhesion31;
        this.film_adhesion41 = film_adhesion41;
        this.leg_height_vertical1 = leg_height_vertical1;
        this.leg_height_across1 = leg_height_across1;
        this.carbonation_depth1 = carbonation_depth1;
        this.carbonation_depth2 = carbonation_depth2;
        this.carbonation_depth3 = carbonation_depth3;
        this.carbonation_depth4 = carbonation_depth4;
        this.carbonation_depth5 = carbonation_depth5;
        this.carbonation_depth6 = carbonation_depth6;
        this.carbonation_depth7 = carbonation_depth7;
        this.carbonation_depth8 = carbonation_depth8;
        this.carbonation_depth9 = carbonation_depth9;
        this.carbonation_depth10 = carbonation_depth10;
        this.carbonation_depth = carbonation_depth;
        this.carbonation_depth_avg = carbonation_depth_avg;
        this.springback_prediction = springback_prediction;
        this.crebound_file_name = crebound_file_name;
        this.rebound_file_name = rebound_file_name;
        this.protective_layer_thickness = protective_layer_thickness;
        this.steelbar_spacing = steelbar_spacing;
        this.bar_spacing_file_name = bar_spacing_file_name;
        this.door_leaf_base_vent_width1 = door_leaf_base_vent_width1;
        this.door_leaf_base_vent_height1 = door_leaf_base_vent_height1;
        this.hanging_plate_thickness_top1 = hanging_plate_thickness_top1;
        this.hanging_plate_thickness_bottom1 = hanging_plate_thickness_bottom1;
        this.inside_plate_thickness1 = inside_plate_thickness1;
        this.surface_gap1 = surface_gap1;
        this.lm_fillister_left_top = lm_fillister_left_top;
        this.lm_fillister_left_center = lm_fillister_left_center;
        this.lm_fillister_left_bottom = lm_fillister_left_bottom;
        this.lm_fillister_right_top = lm_fillister_right_top;
        this.lm_fillister_right_center = lm_fillister_right_center;
        this.lm_fillister_right_bottom = lm_fillister_right_bottom;
        this.lm_indentation_left_top = lm_indentation_left_top;
        this.lm_indentation_left_center = lm_indentation_left_center;
        this.lm_indentation_left_bottom = lm_indentation_left_bottom;
        this.lm_indentation_right_top = lm_indentation_right_top;
        this.lm_indentation_right_center = lm_indentation_right_center;
        this.lm_indentation_right_bottom = lm_indentation_right_bottom;
        this.panel_thickness = panel_thickness;
        this.blm_fillister_left_top = blm_fillister_left_top;
        this.blm_fillister_left_center = blm_fillister_left_center;
        this.blm_fillister_left_bottomp = blm_fillister_left_bottomp;
        this.blm_fillister_left_bottom = blm_fillister_left_bottom;
        this.blm_fillister_right_top = blm_fillister_right_top;
        this.blm_fillister_right_center = blm_fillister_right_center;
        this.blm_fillister_right_bottom = blm_fillister_right_bottom;
        this.hinge_axis_perpendicularity_bothway = hinge_axis_perpendicularity_bothway;
        this.hinge_axis_perpendicularity_vertical = hinge_axis_perpendicularity_vertical;
        this.hinge_axis_perpendicularity_parallel = hinge_axis_perpendicularity_parallel;
        this.frame_inter_wall_verticality1 = frame_inter_wall_verticality1;
        this.frame_inter_wall_verticality2 = frame_inter_wall_verticality2;
        this.frame_inter_wall_verticality3 = frame_inter_wall_verticality3;
        this.frame_inter_wall_verticality4 = frame_inter_wall_verticality4;
        this.line_hammer_specifications = line_hammer_specifications;
        this.door_open_force1 = door_open_force1;
        this.door_close_force1 = door_close_force1;
        this.lock_control_force1 = lock_control_force1;
        this.lock_control_forceNM1 = lock_control_forceNM1;
        this.handleLength = handleLength;
        this.performance_of_door_fans_left = performance_of_door_fans_left;
        this.door_fan_ambient_noise_left = door_fan_ambient_noise_left;
        this.door_leaf_noise_left = door_leaf_noise_left;
        this.performance_of_door_fans_right = performance_of_door_fans_right;
        this.door_fan_ambient_noise_right = door_fan_ambient_noise_right;
        this.door_leaf_noise_right = door_leaf_noise_right;
        this.close_dangling_board_starting_force_top1 = close_dangling_board_starting_force_top1;
        this.swing_plate_quality = swing_plate_quality;
        this.swing_plate_hingeseat_quality = swing_plate_hingeseat_quality;
        this.swing_plate_closing_the_most_strongly_top1 = swing_plate_closing_the_most_strongly_top1;
        this.swing_plate_biggest_gap_between_door_leaf_top1 = swing_plate_biggest_gap_between_door_leaf_top1;
        this.door_frame_leaf_max_clearance1 = door_frame_leaf_max_clearance1;
    }

    @Generated(hash = 8703597)
    public SampleCheck() {
    }







    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getDoor_frame_height1() {
        return door_frame_height1;
    }

    public void setDoor_frame_height1(String door_frame_height1) {
        this.door_frame_height1 = door_frame_height1;
    }

    public String getDoor_frame_width1() {
        return door_frame_width1;
    }

    public void setDoor_frame_width1(String door_frame_width1) {
        this.door_frame_width1 = door_frame_width1;
    }

    public String getDoor_frame_diagonal() {
        return door_frame_diagonal;
    }

    public void setDoor_frame_diagonal(String door_frame_diagonal) {
        this.door_frame_diagonal = door_frame_diagonal;
    }

    public String getDoor_frame_diagonal_bottom_fulcrum() {
        return door_frame_diagonal_bottom_fulcrum;
    }

    public void setDoor_frame_diagonal_bottom_fulcrum(String door_frame_diagonal_bottom_fulcrum) {
        this.door_frame_diagonal_bottom_fulcrum = door_frame_diagonal_bottom_fulcrum;
    }

    public String getDoor_frame_diagonal_bottom_fulcrum_difference() {
        return door_frame_diagonal_bottom_fulcrum_difference;
    }

    public void setDoor_frame_diagonal_bottom_fulcrum_difference(String door_frame_diagonal_bottom_fulcrum_difference) {
        this.door_frame_diagonal_bottom_fulcrum_difference = door_frame_diagonal_bottom_fulcrum_difference;
    }

    public String getDoor_frame_diagonal_top_fulcrum() {
        return door_frame_diagonal_top_fulcrum;
    }

    public void setDoor_frame_diagonal_top_fulcrum(String door_frame_diagonal_top_fulcrum) {
        this.door_frame_diagonal_top_fulcrum = door_frame_diagonal_top_fulcrum;
    }

    public String getDoor_frame_diagonal_top_fulcrum_difference() {
        return door_frame_diagonal_top_fulcrum_difference;
    }

    public void setDoor_frame_diagonal_top_fulcrum_difference(String door_frame_diagonal_top_fulcrum_difference) {
        this.door_frame_diagonal_top_fulcrum_difference = door_frame_diagonal_top_fulcrum_difference;
    }

    public String getDoor_leaf_height1() {
        return door_leaf_height1;
    }

    public void setDoor_leaf_height1(String door_leaf_height1) {
        this.door_leaf_height1 = door_leaf_height1;
    }

    public String getDoor_leaf_width1() {
        return door_leaf_width1;
    }

    public void setDoor_leaf_width1(String door_leaf_width1) {
        this.door_leaf_width1 = door_leaf_width1;
    }

    public String getDoor_leaf_diagonal() {
        return door_leaf_diagonal;
    }

    public void setDoor_leaf_diagonal(String door_leaf_diagonal) {
        this.door_leaf_diagonal = door_leaf_diagonal;
    }

    public String getDoor_leaf_thickess() {
        return door_leaf_thickess;
    }

    public void setDoor_leaf_thickess(String door_leaf_thickess) {
        this.door_leaf_thickess = door_leaf_thickess;
    }

    public String getEmbedded_plate_height1() {
        return embedded_plate_height1;
    }

    public void setEmbedded_plate_height1(String embedded_plate_height1) {
        this.embedded_plate_height1 = embedded_plate_height1;
    }

    public String getHanging_plate_height1() {
        return hanging_plate_height1;
    }

    public void setHanging_plate_height1(String hanging_plate_height1) {
        this.hanging_plate_height1 = hanging_plate_height1;
    }

    public String getHanging_plate_height_center1() {
        return hanging_plate_height_center1;
    }

    public void setHanging_plate_height_center1(String hanging_plate_height_center1) {
        this.hanging_plate_height_center1 = hanging_plate_height_center1;
    }

    public String getHanging_plate_height_bottom1() {
        return hanging_plate_height_bottom1;
    }

    public void setHanging_plate_height_bottom1(String hanging_plate_height_bottom1) {
        this.hanging_plate_height_bottom1 = hanging_plate_height_bottom1;
    }

    public String getHanging_plate_width1() {
        return hanging_plate_width1;
    }

    public void setHanging_plate_width1(String hanging_plate_width1) {
        this.hanging_plate_width1 = hanging_plate_width1;
    }

    public String getHanging_plate_width_center1() {
        return hanging_plate_width_center1;
    }

    public void setHanging_plate_width_center1(String hanging_plate_width_center1) {
        this.hanging_plate_width_center1 = hanging_plate_width_center1;
    }

    public String getHanging_plate_width_bottom1() {
        return hanging_plate_width_bottom1;
    }

    public void setHanging_plate_width_bottom1(String hanging_plate_width_bottom1) {
        this.hanging_plate_width_bottom1 = hanging_plate_width_bottom1;
    }

    public String getSwing_plate_diagonal1() {
        return swing_plate_diagonal1;
    }

    public void setSwing_plate_diagonal1(String swing_plate_diagonal1) {
        this.swing_plate_diagonal1 = swing_plate_diagonal1;
    }

    public String getSwing_plate_diagonal2() {
        return swing_plate_diagonal2;
    }

    public void setSwing_plate_diagonal2(String swing_plate_diagonal2) {
        this.swing_plate_diagonal2 = swing_plate_diagonal2;
    }

    public String getHingepage_shaft_diameter1() {
        return hingepage_shaft_diameter1;
    }

    public void setHingepage_shaft_diameter1(String hingepage_shaft_diameter1) {
        this.hingepage_shaft_diameter1 = hingepage_shaft_diameter1;
    }

    public String getHingepage_hole_diameter1() {
        return hingepage_hole_diameter1;
    }

    public void setHingepage_hole_diameter1(String hingepage_hole_diameter1) {
        this.hingepage_hole_diameter1 = hingepage_hole_diameter1;
    }

    public String getAtresia_shaft_diameter1() {
        return atresia_shaft_diameter1;
    }

    public void setAtresia_shaft_diameter1(String atresia_shaft_diameter1) {
        this.atresia_shaft_diameter1 = atresia_shaft_diameter1;
    }

    public String getAtresia_hole_diameter1() {
        return atresia_hole_diameter1;
    }

    public void setAtresia_hole_diameter1(String atresia_hole_diameter1) {
        this.atresia_hole_diameter1 = atresia_hole_diameter1;
    }

    public String getDoor_frame_surface1() {
        return door_frame_surface1;
    }

    public void setDoor_frame_surface1(String door_frame_surface1) {
        this.door_frame_surface1 = door_frame_surface1;
    }

    public String getDoor_leaf_surface1() {
        return door_leaf_surface1;
    }

    public void setDoor_leaf_surface1(String door_leaf_surface1) {
        this.door_leaf_surface1 = door_leaf_surface1;
    }

    public String getSwing_plate_flatness1() {
        return swing_plate_flatness1;
    }

    public void setSwing_plate_flatness1(String swing_plate_flatness1) {
        this.swing_plate_flatness1 = swing_plate_flatness1;
    }

    public String getDoor_frame_left1() {
        return door_frame_left1;
    }

    public void setDoor_frame_left1(String door_frame_left1) {
        this.door_frame_left1 = door_frame_left1;
    }

    public String getDoor_frame_right1() {
        return door_frame_right1;
    }

    public void setDoor_frame_right1(String door_frame_right1) {
        this.door_frame_right1 = door_frame_right1;
    }

    public String getDoor_leaf_positive1() {
        return door_leaf_positive1;
    }

    public void setDoor_leaf_positive1(String door_leaf_positive1) {
        this.door_leaf_positive1 = door_leaf_positive1;
    }

    public String getDoor_leaf_opposite1() {
        return door_leaf_opposite1;
    }

    public void setDoor_leaf_opposite1(String door_leaf_opposite1) {
        this.door_leaf_opposite1 = door_leaf_opposite1;
    }

    public String getFilm_thickness11() {
        return film_thickness11;
    }

    public void setFilm_thickness11(String film_thickness11) {
        this.film_thickness11 = film_thickness11;
    }

    public String getFilm_thickness21() {
        return film_thickness21;
    }

    public void setFilm_thickness21(String film_thickness21) {
        this.film_thickness21 = film_thickness21;
    }

    public String getFilm_thickness31() {
        return film_thickness31;
    }

    public void setFilm_thickness31(String film_thickness31) {
        this.film_thickness31 = film_thickness31;
    }

    public String getFilm_thickness41() {
        return film_thickness41;
    }

    public void setFilm_thickness41(String film_thickness41) {
        this.film_thickness41 = film_thickness41;
    }

    public String getFilm_adhesion11() {
        return film_adhesion11;
    }

    public void setFilm_adhesion11(String film_adhesion11) {
        this.film_adhesion11 = film_adhesion11;
    }

    public String getFilm_adhesion21() {
        return film_adhesion21;
    }

    public void setFilm_adhesion21(String film_adhesion21) {
        this.film_adhesion21 = film_adhesion21;
    }

    public String getFilm_adhesion31() {
        return film_adhesion31;
    }

    public void setFilm_adhesion31(String film_adhesion31) {
        this.film_adhesion31 = film_adhesion31;
    }

    public String getFilm_adhesion41() {
        return film_adhesion41;
    }

    public void setFilm_adhesion41(String film_adhesion41) {
        this.film_adhesion41 = film_adhesion41;
    }

    public String getLeg_height_vertical1() {
        return leg_height_vertical1;
    }

    public void setLeg_height_vertical1(String leg_height_vertical1) {
        this.leg_height_vertical1 = leg_height_vertical1;
    }

    public String getLeg_height_across1() {
        return leg_height_across1;
    }

    public void setLeg_height_across1(String leg_height_across1) {
        this.leg_height_across1 = leg_height_across1;
    }

    public String getCarbonation_depth1() {
        return carbonation_depth1;
    }

    public void setCarbonation_depth1(String carbonation_depth1) {
        this.carbonation_depth1 = carbonation_depth1;
    }

    public String getCarbonation_depth2() {
        return carbonation_depth2;
    }

    public void setCarbonation_depth2(String carbonation_depth2) {
        this.carbonation_depth2 = carbonation_depth2;
    }

    public String getCarbonation_depth3() {
        return carbonation_depth3;
    }

    public void setCarbonation_depth3(String carbonation_depth3) {
        this.carbonation_depth3 = carbonation_depth3;
    }

    public String getCarbonation_depth4() {
        return carbonation_depth4;
    }

    public void setCarbonation_depth4(String carbonation_depth4) {
        this.carbonation_depth4 = carbonation_depth4;
    }

    public String getCarbonation_depth5() {
        return carbonation_depth5;
    }

    public void setCarbonation_depth5(String carbonation_depth5) {
        this.carbonation_depth5 = carbonation_depth5;
    }

    public String getCarbonation_depth6() {
        return carbonation_depth6;
    }

    public void setCarbonation_depth6(String carbonation_depth6) {
        this.carbonation_depth6 = carbonation_depth6;
    }

    public String getCarbonation_depth7() {
        return carbonation_depth7;
    }

    public void setCarbonation_depth7(String carbonation_depth7) {
        this.carbonation_depth7 = carbonation_depth7;
    }

    public String getCarbonation_depth8() {
        return carbonation_depth8;
    }

    public void setCarbonation_depth8(String carbonation_depth8) {
        this.carbonation_depth8 = carbonation_depth8;
    }

    public String getCarbonation_depth9() {
        return carbonation_depth9;
    }

    public void setCarbonation_depth9(String carbonation_depth9) {
        this.carbonation_depth9 = carbonation_depth9;
    }

    public String getCarbonation_depth10() {
        return carbonation_depth10;
    }

    public void setCarbonation_depth10(String carbonation_depth10) {
        this.carbonation_depth10 = carbonation_depth10;
    }

    public String getCarbonation_depth() {
        return carbonation_depth;
    }

    public void setCarbonation_depth(String carbonation_depth) {
        this.carbonation_depth = carbonation_depth;
    }

    public String getCarbonation_depth_avg() {
        return carbonation_depth_avg;
    }

    public void setCarbonation_depth_avg(String carbonation_depth_avg) {
        this.carbonation_depth_avg = carbonation_depth_avg;
    }

    public String getSpringback_prediction() {
        return springback_prediction;
    }

    public void setSpringback_prediction(String springback_prediction) {
        this.springback_prediction = springback_prediction;
    }

    public String getCrebound_file_name() {
        return crebound_file_name;
    }

    public void setCrebound_file_name(String crebound_file_name) {
        this.crebound_file_name = crebound_file_name;
    }

    public String getProtective_layer_thickness() {
        return protective_layer_thickness;
    }

    public void setProtective_layer_thickness(String protective_layer_thickness) {
        this.protective_layer_thickness = protective_layer_thickness;
    }

    public String getSteelbar_spacing() {
        return steelbar_spacing;
    }

    public void setSteelbar_spacing(String steelbar_spacing) {
        this.steelbar_spacing = steelbar_spacing;
    }

    public String getBar_spacing_file_name() {
        return bar_spacing_file_name;
    }

    public void setBar_spacing_file_name(String bar_spacing_file_name) {
        this.bar_spacing_file_name = bar_spacing_file_name;
    }

    public String getDoor_leaf_base_vent_width1() {
        return door_leaf_base_vent_width1;
    }

    public void setDoor_leaf_base_vent_width1(String door_leaf_base_vent_width1) {
        this.door_leaf_base_vent_width1 = door_leaf_base_vent_width1;
    }

    public String getDoor_leaf_base_vent_height1() {
        return door_leaf_base_vent_height1;
    }

    public void setDoor_leaf_base_vent_height1(String door_leaf_base_vent_height1) {
        this.door_leaf_base_vent_height1 = door_leaf_base_vent_height1;
    }

    public String getHanging_plate_thickness_top1() {
        return hanging_plate_thickness_top1;
    }

    public void setHanging_plate_thickness_top1(String hanging_plate_thickness_top1) {
        this.hanging_plate_thickness_top1 = hanging_plate_thickness_top1;
    }

    public String getHanging_plate_thickness_bottom1() {
        return hanging_plate_thickness_bottom1;
    }

    public void setHanging_plate_thickness_bottom1(String hanging_plate_thickness_bottom1) {
        this.hanging_plate_thickness_bottom1 = hanging_plate_thickness_bottom1;
    }

    public String getInside_plate_thickness1() {
        return inside_plate_thickness1;
    }

    public void setInside_plate_thickness1(String inside_plate_thickness1) {
        this.inside_plate_thickness1 = inside_plate_thickness1;
    }

    public String getSurface_gap1() {
        return surface_gap1;
    }

    public void setSurface_gap1(String surface_gap1) {
        this.surface_gap1 = surface_gap1;
    }

    public String getLm_fillister_left_top() {
        return lm_fillister_left_top;
    }

    public void setLm_fillister_left_top(String lm_fillister_left_top) {
        this.lm_fillister_left_top = lm_fillister_left_top;
    }

    public String getLm_fillister_left_center() {
        return lm_fillister_left_center;
    }

    public void setLm_fillister_left_center(String lm_fillister_left_center) {
        this.lm_fillister_left_center = lm_fillister_left_center;
    }

    public String getLm_fillister_left_bottom() {
        return lm_fillister_left_bottom;
    }

    public void setLm_fillister_left_bottom(String lm_fillister_left_bottom) {
        this.lm_fillister_left_bottom = lm_fillister_left_bottom;
    }

    public String getLm_fillister_right_top() {
        return lm_fillister_right_top;
    }

    public void setLm_fillister_right_top(String lm_fillister_right_top) {
        this.lm_fillister_right_top = lm_fillister_right_top;
    }

    public String getLm_fillister_right_center() {
        return lm_fillister_right_center;
    }

    public void setLm_fillister_right_center(String lm_fillister_right_center) {
        this.lm_fillister_right_center = lm_fillister_right_center;
    }

    public String getLm_fillister_right_bottom() {
        return lm_fillister_right_bottom;
    }

    public void setLm_fillister_right_bottom(String lm_fillister_right_bottom) {
        this.lm_fillister_right_bottom = lm_fillister_right_bottom;
    }

    public String getLm_indentation_left_top() {
        return lm_indentation_left_top;
    }

    public void setLm_indentation_left_top(String lm_indentation_left_top) {
        this.lm_indentation_left_top = lm_indentation_left_top;
    }

    public String getLm_indentation_left_center() {
        return lm_indentation_left_center;
    }

    public void setLm_indentation_left_center(String lm_indentation_left_center) {
        this.lm_indentation_left_center = lm_indentation_left_center;
    }

    public String getLm_indentation_left_bottom() {
        return lm_indentation_left_bottom;
    }

    public void setLm_indentation_left_bottom(String lm_indentation_left_bottom) {
        this.lm_indentation_left_bottom = lm_indentation_left_bottom;
    }

    public String getLm_indentation_right_top() {
        return lm_indentation_right_top;
    }

    public void setLm_indentation_right_top(String lm_indentation_right_top) {
        this.lm_indentation_right_top = lm_indentation_right_top;
    }

    public String getLm_indentation_right_center() {
        return lm_indentation_right_center;
    }

    public void setLm_indentation_right_center(String lm_indentation_right_center) {
        this.lm_indentation_right_center = lm_indentation_right_center;
    }

    public String getLm_indentation_right_bottom() {
        return lm_indentation_right_bottom;
    }

    public void setLm_indentation_right_bottom(String lm_indentation_right_bottom) {
        this.lm_indentation_right_bottom = lm_indentation_right_bottom;
    }

    public String getPanel_thickness() {
        return panel_thickness;
    }

    public void setPanel_thickness(String panel_thickness) {
        this.panel_thickness = panel_thickness;
    }

    public String getBlm_fillister_left_top() {
        return blm_fillister_left_top;
    }

    public void setBlm_fillister_left_top(String blm_fillister_left_top) {
        this.blm_fillister_left_top = blm_fillister_left_top;
    }

    public String getBlm_fillister_left_center() {
        return blm_fillister_left_center;
    }

    public void setBlm_fillister_left_center(String blm_fillister_left_center) {
        this.blm_fillister_left_center = blm_fillister_left_center;
    }

    public String getBlm_fillister_left_bottomp() {
        return blm_fillister_left_bottomp;
    }

    public void setBlm_fillister_left_bottomp(String blm_fillister_left_bottomp) {
        this.blm_fillister_left_bottomp = blm_fillister_left_bottomp;
    }

    public String getBlm_fillister_right_top() {
        return blm_fillister_right_top;
    }

    public void setBlm_fillister_right_top(String blm_fillister_right_top) {
        this.blm_fillister_right_top = blm_fillister_right_top;
    }

    public String getBlm_fillister_right_center() {
        return blm_fillister_right_center;
    }

    public void setBlm_fillister_right_center(String blm_fillister_right_center) {
        this.blm_fillister_right_center = blm_fillister_right_center;
    }

    public String getBlm_fillister_right_bottom() {
        return blm_fillister_right_bottom;
    }

    public void setBlm_fillister_right_bottom(String blm_fillister_right_bottom) {
        this.blm_fillister_right_bottom = blm_fillister_right_bottom;
    }

    public String getHinge_axis_perpendicularity_bothway() {
        return hinge_axis_perpendicularity_bothway;
    }

    public void setHinge_axis_perpendicularity_bothway(String hinge_axis_perpendicularity_bothway) {
        this.hinge_axis_perpendicularity_bothway = hinge_axis_perpendicularity_bothway;
    }

    public String getHinge_axis_perpendicularity_vertical() {
        return hinge_axis_perpendicularity_vertical;
    }

    public void setHinge_axis_perpendicularity_vertical(String hinge_axis_perpendicularity_vertical) {
        this.hinge_axis_perpendicularity_vertical = hinge_axis_perpendicularity_vertical;
    }

    public String getHinge_axis_perpendicularity_parallel() {
        return hinge_axis_perpendicularity_parallel;
    }

    public void setHinge_axis_perpendicularity_parallel(String hinge_axis_perpendicularity_parallel) {
        this.hinge_axis_perpendicularity_parallel = hinge_axis_perpendicularity_parallel;
    }

    public String getFrame_inter_wall_verticality1() {
        return frame_inter_wall_verticality1;
    }

    public void setFrame_inter_wall_verticality1(String frame_inter_wall_verticality1) {
        this.frame_inter_wall_verticality1 = frame_inter_wall_verticality1;
    }

    public String getFrame_inter_wall_verticality2() {
        return frame_inter_wall_verticality2;
    }

    public void setFrame_inter_wall_verticality2(String frame_inter_wall_verticality2) {
        this.frame_inter_wall_verticality2 = frame_inter_wall_verticality2;
    }

    public String getFrame_inter_wall_verticality3() {
        return frame_inter_wall_verticality3;
    }

    public void setFrame_inter_wall_verticality3(String frame_inter_wall_verticality3) {
        this.frame_inter_wall_verticality3 = frame_inter_wall_verticality3;
    }

    public String getFrame_inter_wall_verticality4() {
        return frame_inter_wall_verticality4;
    }

    public void setFrame_inter_wall_verticality4(String frame_inter_wall_verticality4) {
        this.frame_inter_wall_verticality4 = frame_inter_wall_verticality4;
    }

    public String getLine_hammer_specifications() {
        return line_hammer_specifications;
    }

    public void setLine_hammer_specifications(String line_hammer_specifications) {
        this.line_hammer_specifications = line_hammer_specifications;
    }

    public String getDoor_open_force1() {
        return door_open_force1;
    }

    public void setDoor_open_force1(String door_open_force1) {
        this.door_open_force1 = door_open_force1;
    }

    public String getDoor_close_force1() {
        return door_close_force1;
    }

    public void setDoor_close_force1(String door_close_force1) {
        this.door_close_force1 = door_close_force1;
    }

    public String getLock_control_force1() {
        return lock_control_force1;
    }

    public void setLock_control_force1(String lock_control_force1) {
        this.lock_control_force1 = lock_control_force1;
    }

    public String getLock_control_forceNM1() {
        return lock_control_forceNM1;
    }

    public void setLock_control_forceNM1(String lock_control_forceNM1) {
        this.lock_control_forceNM1 = lock_control_forceNM1;
    }

    public String getHandleLength() {
        return handleLength;
    }

    public void setHandleLength(String handleLength) {
        this.handleLength = handleLength;
    }

    public String getPerformance_of_door_fans_left() {
        return performance_of_door_fans_left;
    }

    public void setPerformance_of_door_fans_left(String performance_of_door_fans_left) {
        this.performance_of_door_fans_left = performance_of_door_fans_left;
    }

    public String getDoor_fan_ambient_noise_left() {
        return door_fan_ambient_noise_left;
    }

    public void setDoor_fan_ambient_noise_left(String door_fan_ambient_noise_left) {
        this.door_fan_ambient_noise_left = door_fan_ambient_noise_left;
    }

    public String getDoor_leaf_noise_left() {
        return door_leaf_noise_left;
    }

    public void setDoor_leaf_noise_left(String door_leaf_noise_left) {
        this.door_leaf_noise_left = door_leaf_noise_left;
    }

    public String getPerformance_of_door_fans_right() {
        return performance_of_door_fans_right;
    }

    public void setPerformance_of_door_fans_right(String performance_of_door_fans_right) {
        this.performance_of_door_fans_right = performance_of_door_fans_right;
    }

    public String getDoor_fan_ambient_noise_right() {
        return door_fan_ambient_noise_right;
    }

    public void setDoor_fan_ambient_noise_right(String door_fan_ambient_noise_right) {
        this.door_fan_ambient_noise_right = door_fan_ambient_noise_right;
    }

    public String getDoor_leaf_noise_right() {
        return door_leaf_noise_right;
    }

    public void setDoor_leaf_noise_right(String door_leaf_noise_right) {
        this.door_leaf_noise_right = door_leaf_noise_right;
    }

    public String getClose_dangling_board_starting_force_top1() {
        return close_dangling_board_starting_force_top1;
    }

    public void setClose_dangling_board_starting_force_top1(String close_dangling_board_starting_force_top1) {
        this.close_dangling_board_starting_force_top1 = close_dangling_board_starting_force_top1;
    }

    public String getSwing_plate_quality() {
        return swing_plate_quality;
    }

    public void setSwing_plate_quality(String swing_plate_quality) {
        this.swing_plate_quality = swing_plate_quality;
    }

    public String getSwing_plate_hingeseat_quality() {
        return swing_plate_hingeseat_quality;
    }

    public void setSwing_plate_hingeseat_quality(String swing_plate_hingeseat_quality) {
        this.swing_plate_hingeseat_quality = swing_plate_hingeseat_quality;
    }

    public String getSwing_plate_closing_the_most_strongly_top1() {
        return swing_plate_closing_the_most_strongly_top1;
    }

    public void setSwing_plate_closing_the_most_strongly_top1(String swing_plate_closing_the_most_strongly_top1) {
        this.swing_plate_closing_the_most_strongly_top1 = swing_plate_closing_the_most_strongly_top1;
    }

    public String getSwing_plate_biggest_gap_between_door_leaf_top1() {
        return swing_plate_biggest_gap_between_door_leaf_top1;
    }

    public void setSwing_plate_biggest_gap_between_door_leaf_top1(String swing_plate_biggest_gap_between_door_leaf_top1) {
        this.swing_plate_biggest_gap_between_door_leaf_top1 = swing_plate_biggest_gap_between_door_leaf_top1;
    }

    public String getDoor_frame_leaf_max_clearance1() {
        return door_frame_leaf_max_clearance1;
    }

    public void setDoor_frame_leaf_max_clearance1(String door_frame_leaf_max_clearance1) {
        this.door_frame_leaf_max_clearance1 = door_frame_leaf_max_clearance1;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getPid() {
        return this.pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }
}
