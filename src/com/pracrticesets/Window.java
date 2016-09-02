package com.pracrticesets;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.BorderLayout;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class Window extends JFrame {

    private Set<String> contentListOne;
    private Set<String> contentListTwo;

    public Window() {
        super("Conjuntos");
        initComponents(this);
        this.contentListOne = new HashSet<>();
        this.contentListTwo = new HashSet<>();
    }

    private void initComponents(JFrame frame) {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 400));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());

        // Primary components
        JPanel controlsPane = new JPanel();
        controlsPane.setLayout(null);

        JLabel labelSetOne = new JLabel("Conjunto A:");
        JLabel labelSetTwo = new JLabel("Conjunto B:");
        JLabel labelResult = new JLabel("Resultado:");
        JLabel labelSetOneContent = new JLabel();
        JLabel labelSetTwoContent = new JLabel();
        JLabel labelResultContent = new JLabel();

        labelSetOne.setBounds(20, 5, 100, 100);
        labelSetOneContent.setBounds(140, 3, 300, 100);
        labelSetTwo.setBounds(20, 35, 100, 100);
        labelSetTwoContent.setBounds(140, 35, 300, 100);
        labelResult.setBounds(20, 65, 100, 100);
        labelResultContent.setBounds(140, 65, 600, 100);

        JLabel labelData = new JLabel("Dato:");
        JLabel labelOperation = new JLabel("Operacion:");
        JTextField dataField = new JTextField(5);
        JButton buttonAdd = new JButton("Agregar");
        JButton buttonAccept = new JButton("Aceptar");
        ButtonGroup groupSets = new ButtonGroup();
        JRadioButton setOneRadio = new JRadioButton("Conjunto A", true);
        JRadioButton setTwoRadio = new JRadioButton("Conjunto B");
        ButtonGroup groupOperations = new ButtonGroup();
        JRadioButton unionRadio = new JRadioButton("Union", true);
        JRadioButton intersectionRadio = new JRadioButton("Interseccion");
        JRadioButton differenceRadio = new JRadioButton("Diferencia");

        labelData.setBounds(20, 165, 100, 100);
        dataField.setBounds(20, 230, 100, 30);
        setOneRadio.setBounds(20, 250, 150, 45);
        setTwoRadio.setBounds(20, 280, 150, 45);
        buttonAdd.setBounds(20, 330, 100, 25);
        labelOperation.setBounds(250, 165, 100, 100);
        unionRadio.setBounds(250, 230, 150, 45);
        intersectionRadio.setBounds(250, 260, 150, 45);
        differenceRadio.setBounds(250, 290, 150, 45);
        buttonAccept.setBounds(400, 275, 100, 25);

        groupSets.add(setOneRadio);
        groupSets.add(setTwoRadio);
        groupOperations.add(unionRadio);
        groupOperations.add(intersectionRadio);
        groupOperations.add(differenceRadio);

        controlsPane.add(labelSetOne);
        controlsPane.add(labelSetOneContent);
        controlsPane.add(labelSetTwo);
        controlsPane.add(labelSetTwoContent);
        controlsPane.add(labelResult);
        controlsPane.add(labelResultContent);

        controlsPane.add(labelData);
        controlsPane.add(dataField);
        controlsPane.add(setOneRadio);
        controlsPane.add(setTwoRadio);
        controlsPane.add(buttonAdd);
        controlsPane.add(labelOperation);
        controlsPane.add(unionRadio);
        controlsPane.add(intersectionRadio);
        controlsPane.add(differenceRadio);
        controlsPane.add(buttonAccept);

        buttonAdd.addActionListener(e -> {
            //Validation to prevent empty set
            if (dataField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Error: No hay nada que agregar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
                try{
                    if (setOneRadio.isSelected()) {
                        contentListOne.add(dataField.getText());
                        labelSetOneContent.setText(Arrays.toString(contentListOne.toArray()));
                    } else {
                        contentListTwo.add(dataField.getText());
                        labelSetTwoContent.setText(Arrays.toString(contentListTwo.toArray()));
                    }
                }
                catch (Exception a){
                    JOptionPane.showMessageDialog(null,"Solo se admiten numeros!","Error!",JOptionPane.ERROR_MESSAGE);
                }
            }

            dataField.setText(null);
        });

        buttonAccept.addActionListener(e -> {
            if (unionRadio.isSelected()) {
                labelResultContent.setText(Arrays.toString(unionList(contentListOne, contentListTwo).toArray()));
            } else if (intersectionRadio.isSelected()) {
                labelResultContent.setText(Arrays.toString(intersectionList(contentListOne, contentListTwo).toArray()));
            } else if (differenceRadio.isSelected()) {
                labelResultContent.setText(Arrays.toString(differenceList(contentListOne, contentListTwo).toArray()));
            }
            //Verifies that one or both sets have at leats one element
            if(labelSetOneContent.getText().isEmpty()  || labelSetTwoContent.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los conjuntos no son correnctos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        container.add(controlsPane, BorderLayout.CENTER);
    }

    private Set<String> unionList(Set<String> first, Set<String> second) {
        Set<String> result = new HashSet<>(first.size() + second.size());
        result.addAll(first);
        result.addAll(second);

        return result;
    }

    private Set<String> intersectionList(Set<String> first, Set<String> second) {
        Set<String> result = new HashSet<>(first.size() > second.size() ? first.size() : second.size());
        result.addAll(first);
        result.retainAll(second);

        return result;
    }

    private Set<String> differenceList(Set<String> first, Set<String> second) {
        Set<String> result = new HashSet<>(first);
        result.removeAll(second);

        return result;
    }
}
