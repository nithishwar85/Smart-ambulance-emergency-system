import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.raghav.sos.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<ContactModel> {

    private final Context context;
    private final List<ContactModel> contacts;

    public CustomAdapter(@NonNull Context context, List<ContactModel> contacts) {
        super(context, 0, contacts);
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        final DbHelper db = new DbHelper(context);

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_user, parent, false);
        }

        final ContactModel contact = getItem(position);

        if (contact == null) {
            return convertView;
        }

        LinearLayout linearLayout = convertView.findViewById(R.id.linear);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPhone = convertView.findViewById(R.id.tvPhone);

        tvName.setText(contact.getName());
        tvPhone.setText(contact.getPhoneNo());

        linearLayout.setOnLongClickListener(view -> {

            new MaterialAlertDialogBuilder(context)
                    .setTitle("Remove Contact")
                    .setMessage("Are you sure you want to remove this contact?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        db.deleteContact(contact);
                        contacts.remove(contact);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Contact removed!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();

            return true;
        });

        return convertView;
    }

    public void refresh(List<ContactModel> list) {
        contacts.clear();
        contacts.addAll(list);
        notifyDataSetChanged();
    }
}
