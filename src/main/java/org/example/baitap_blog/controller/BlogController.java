package org.example.baitap_blog.controller;


import org.example.baitap_blog.model.Blog;
import org.example.baitap_blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BlogController {
    private static final String MSG_CREATE = "Blog created successfully.";
    private static final String MSG_UPDATE = "Blog updated successfully.";
    private static final String MSG_DELETE = "Blog deleted successfully.";
    @Autowired
    BlogService objectService;

    @GetMapping("/objectList")
    public ModelAndView getListPage(){
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("objList", objectService.findAll());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getEditPage(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("obj", objectService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView getEdited(@ModelAttribute Blog obj){
        objectService.update(obj);
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("objList", objectService.findAll());
        modelAndView.addObject("msg", MSG_UPDATE);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView getViewPage(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/blog/view");
        modelAndView.addObject("obj", objectService.findById(id));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView getDeletePage(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/blog/delete");
        modelAndView.addObject("obj", objectService.findById(id));
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView getDeleted(@ModelAttribute Blog obj){
        objectService.remove(obj.getId());
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("objList", objectService.findAll());
        modelAndView.addObject("msg", MSG_DELETE);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView getCreatePage(){
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("obj", new Blog());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView getCreated(@ModelAttribute Blog obj){
        objectService.save(obj);
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("objList", objectService.findAll());
        modelAndView.addObject("msg", MSG_CREATE);
        return modelAndView;
    }
}