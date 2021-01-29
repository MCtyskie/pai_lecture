package com.pai.application.Views;
import com.pai.application.Models.User;
import com.pai.application.Repositories.UserRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("")
@SpringComponent
@UIScope
public class MainUI extends VerticalLayout {
    private Button adminViewButton;
    private Button userViewButton;
    private EmailField emailField;
    private Label infoLabel;

    private HorizontalLayout hl1;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init(){
        adminViewButton=new Button("ADMIN UI");
        userViewButton=new Button("USER UI");
        emailField=new EmailField();
        emailField.setPlaceholder("email");
        infoLabel=new Label();
        hl1=new HorizontalLayout();

        adminViewButton.addClickListener(l->{
            UI.getCurrent().getPage().setLocation("/adminUI");
        });
        userViewButton.addClickListener(l->{
            String email=emailField.getValue();
            User user=userRepository.findUserByEmail(email).orElse(null);
            if(user!=null){
                UI.getCurrent().getPage().setLocation("/userUI");
            }else{
                infoLabel.setText("no user with such email");
            }
        });
        hl1.add(emailField,userViewButton,infoLabel);
        add(adminViewButton,hl1);
    }
}
