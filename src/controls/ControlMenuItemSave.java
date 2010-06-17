package controls;

import exceptions.FileSecurity;
import exceptions.FilesCorrupted;
import exceptions.FilesNull;
import fenetres.FenetAffiche;
import fenetres.FenetDialogues;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import toolkit.LoadSave;

/**
 *
 * @author grimm
 */
public class ControlMenuItemSave implements ActionListener {

    private FenetAffiche frame;

    public ControlMenuItemSave(FenetAffiche frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        LoadSave ls = new LoadSave(frame);
        try {
            ls.save();
        } catch (FileSecurity ex) {
            new FenetDialogues(frame, ex);
        } catch (FilesNull ex) {
            new FenetDialogues(frame, ex);
        } catch (FilesCorrupted ex) {
            new FenetDialogues(frame, ex);
        }
    }
}
