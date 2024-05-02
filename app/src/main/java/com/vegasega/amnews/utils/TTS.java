package com.vegasega.amnews.utils;

import java.util.regex.Pattern;

public class TTS {
//
//    public static String[] convertFileToParagraph(String fileContent) {
//
////        String pattern = "(?<=(rn|r|n))([ \t]*$)+";
//        String pattern = "([ \\t\\r]*\\n[ \\t\\r]*)+";
//        return Pattern.compile(pattern, Pattern.MULTILINE).split(fileContent);
//    }
//
//    /**
//     * Divides files in to paragraphs
//     */
//    private void divideFileToChunks() {
//        try {
//            currentFileChunks = convertFileToParagraph(fileContent);
//            currentFileChunks = makeSmallChunks(currentFileChunks);
//            addChunksToTTS();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Divides file paragraphs into sentences of 200 characters
//     *
//     * @param currentFileChunks : list of paragraphs
//     * @return : list of divided file
//     */
//    private String[] makeSmallChunks(String[] currentFileChunks) {
//        try {
//            ArrayList<String> smallChunks = new ArrayList<>();
//            for (int i = 0; i < currentFileChunks.length; i++) {
//                String chunk = currentFileChunks[i];
//                if (chunk != null && chunk.length() > 200) {
//                    int length = chunk.length();
//                    int count = length / 200;
//                    int modulo = length % 200;
//                    for (int j = 0; j < count; j++) {
//                        smallChunks.add(chunk.substring(200 * j, (200 * j) + 199));
//                    }
//                    if (modulo > 0) {
//                        smallChunks.add(chunk.substring(chunk.length() - 1 - modulo, chunk.length() - 1));
//                    }
//                } else {
//                    smallChunks.add(chunk);
//                }
//            }
//            return smallChunks.toArray(new String[smallChunks.size()]);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return currentFileChunks;
//        }
//    }
//
//    /**
//     * Add all chunks to TTS(Text to Speech) Engine
//     */
//    private void addChunksToTTS() {
//        try {
//            String[] chunks = getCurrentFileChunks();
//            if (chunks != null && chunks.length > 0) {
//                for (int i = currentChunk; i < chunks.length; i++) {
//                    utterParam.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, String.valueOf(i));
//                    textToSpeech.speak(chunks[i], TextToSpeech.QUEUE_ADD, utterParam);
//                    imgBtnT2SPlay.setImageResource(R.drawable.icon_pause_white);
//                    edtT2SFileContents.setEnabled(false);
//                    isPlaying = true;
//                }
//            }
//
//            if (progressDialog != null && progressDialog.isShowing()) {
//                progressDialog.dismiss();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
