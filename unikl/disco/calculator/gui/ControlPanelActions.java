 /*
 *  (c) 2013 Michael A. Beck, disco | Distributed Computer Systems Lab
 *                                  University of Kaiserslautern, Germany
 *         All Rights Reserved.
 *
 *  This software is work in progress and is released in the hope that it will
 *  be useful to the scientific community. It is provided "as is" without
 *  express or implied warranty, including but not limited to the correctness
 *  of the code or its suitability for any particular purpose.
 *
 *  You are free to use this software for any non-commercial educational or
 *  research purpose, provided that this copyright notice is not removed or
 *  modified. For commercial uses please contact the respective author(s).
 *
 *  If you find our software useful, we would appreciate if you mentioned it
 *  in any publication arising from the use of this software or acknowledge
 *  our work otherwise. We would also like to hear of any fixes or useful
 *  extensions to this software.
 *
 */
package unikl.disco.calculator.gui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import unikl.disco.calculator.SNC;
import unikl.disco.calculator.commands.AddFlowCommand;
import unikl.disco.calculator.commands.AddVertexCommand;
import unikl.disco.calculator.commands.Command;
import unikl.disco.calculator.commands.RemoveFlowCommand;
import unikl.disco.calculator.commands.RemoveVertexCommand;
import unikl.disco.calculator.network.Flow;
import unikl.disco.calculator.network.Vertex;
import unikl.disco.calculator.symbolic_math.Arrival;
import unikl.disco.calculator.symbolic_math.functions.ConstantFunction;

/**
 *
 * @author Sebastian Henningsen
 */
public class ControlPanelActions {

    static class AddNodeAction extends AbstractAction {

        public AddNodeAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("Add Node");
            SNC snc = SNC.getInstance();
            Command cmd = new AddVertexCommand("test", -1.0, -1, snc);
            snc.invokeCommand(cmd);
        }
    }

    static class RemoveNodeAction extends AbstractAction {

        public RemoveNodeAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Displayable[] selectables;
            Map<Integer, Vertex> vertices = SNC.getInstance().getVertices();
            if (!vertices.isEmpty()) {
                selectables = new Displayable[vertices.size()];
                int counter = 0;
                for (Map.Entry<Integer, Vertex> entry : vertices.entrySet()) {
                    Vertex vertex = entry.getValue();
                    selectables[counter] = vertex;
                    counter++;
                }
                Displayable d = (Displayable) JOptionPane.showInputDialog(
                        null,
                        "Please Choose a vertex: ",
                        "Customized Dialog",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        selectables,
                        selectables[0]);
                if (d != null) {
                    Command cmd = new RemoveVertexCommand(d.getID(), -1, SNC.getInstance());
                    SNC.getInstance().invokeCommand(cmd);
                    System.out.println(d.getID() + " " + d.getAlias());
                } else {
                    System.out.println("No vertex selected");
                }
            } else {
                JOptionPane.showMessageDialog(null, "There are no vertices in the network!");
            }
        }
    }

    static class RemoveFlowAction extends AbstractAction {

        public RemoveFlowAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            // Show a dialog where the user can select a flow that will be removed
            // Box from a Map of IDs and Flows to an Array of Displayables which is needed for
            // the JComboBox
            Displayable[] selectables;
            Map<Integer, Flow> flows = SNC.getInstance().getFlows();
            if (!flows.isEmpty()) {
                selectables = new Displayable[flows.size()];
                int counter = 0;
                for (Map.Entry<Integer, Flow> entry : flows.entrySet()) {
                    Flow flow = entry.getValue();
                    selectables[counter] = flow;
                    counter++;
                }
                Displayable d = (Displayable) JOptionPane.showInputDialog(
                        null,
                        "Please choose a flow: ",
                        "Customized Dialog",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        selectables,
                        selectables[0]);
                if (d != null) {
                    Command cmd = new RemoveFlowCommand(d.getID(), -1, SNC.getInstance());
                    SNC.getInstance().invokeCommand(cmd);
                    System.out.println(d.getID() + " " + d.getAlias());
                } else {
                    System.out.println("No flow selected.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "There are no flows in the network!");
            }
        }
    }

    static class AddFlowAction extends AbstractAction {

        public AddFlowAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("Add Flow");
            SNC snc = SNC.getInstance();
            List<Integer> dummyList = new ArrayList();
            dummyList.add(1);
            Command cmd = new AddFlowCommand("test", new Arrival(new ConstantFunction(0), new ConstantFunction(1), snc.getCurrentNetwork()), dummyList, dummyList, -1, snc);
            snc.invokeCommand(cmd);
        }
    }

    static class AnalyzeNetworkAction extends AbstractAction {

        public AnalyzeNetworkAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            AnalyzeDialog2 dialog = new AnalyzeDialog2();
            dialog.display();
        }
    }

    static class OptimizationAction extends AbstractAction {

        public OptimizationAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            OptimizationDialog dialog = new OptimizationDialog();
            dialog.display();
        }
    }
}
