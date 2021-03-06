/*
 * Nextcloud Talk application
 *
 * @author Mario Danic
 * Copyright (C) 2017-2018 Mario Danic <mario@lovelyhq.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.nextcloud.talk.adapters.messages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nextcloud.talk.R;
import com.nextcloud.talk.application.NextcloudTalkApplication;
import com.nextcloud.talk.models.json.chat.ChatMessage;
import com.nextcloud.talk.utils.DisplayUtils;
import com.stfalcon.chatkit.messages.MessageHolders;
import com.vanniktech.emoji.EmojiTextView;

public class MagicPreviewMessageViewHolder extends MessageHolders.IncomingImageMessageViewHolder<ChatMessage> {

    @BindView(R.id.messageText)
    EmojiTextView messageText;

    public MagicPreviewMessageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBind(ChatMessage message) {
        super.onBind(message);

        if (userAvatar != null) {
            if (message.isGrouped) {
                userAvatar.setVisibility(View.INVISIBLE);
            } else {
                userAvatar.setVisibility(View.VISIBLE);
            }
        }

        if (message.getMessageType() == ChatMessage.MessageType.SINGLE_NC_ATTACHMENT_MESSAGE) {
            // it's a preview for a Nextcloud share
            messageText.setText(message.getSelectedIndividualHashMap().get("name"));
            DisplayUtils.setClickableString(message.getSelectedIndividualHashMap().get("name"), message.getSelectedIndividualHashMap().get("link"), messageText);
            image.setOnClickListener(v -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(message.getSelectedIndividualHashMap().get("link")));
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                NextcloudTalkApplication.getSharedApplication().getApplicationContext().startActivity(browserIntent);
            });
        } else if (message.getMessageType() == ChatMessage.MessageType.SINGLE_LINK_GIPHY_MESSAGE) {
            messageText.setText("GIPHY");
            DisplayUtils.setClickableString("GIPHY", "https://giphy.com", messageText);
        } else if (message.getMessageType() == ChatMessage.MessageType.SINGLE_LINK_TENOR_MESSAGE) {
            messageText.setText("Tenor");
            DisplayUtils.setClickableString("Tenor", "https://tenor.com", messageText);
        } else {
            messageText.setText("");
        }
    }
}
