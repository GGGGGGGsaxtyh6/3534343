# Easy Money Sherlock - Task 1

## Question
At what exact time did the user execute the malicious shortcut file?

## Answer
**2025-01-26 16:17:15**

## Evidence

### Artifact: UserAssist (NTUSER.DAT)
- Path: `HKCU\Software\Microsoft\Windows\CurrentVersion\Explorer\UserAssist\{F4E57C4B-2036-45F0-A9AB-443BCFE33D9F}\Count`
- Entry (ROT13 decoded): `C:\Users\Administrator\Downloads\2025-GiveAways.lnk`
- Run count: 1
- Last execution timestamp: 2025-01-26 16:17:15.490 UTC

### Corroborating Evidence
- **$MFT** shows `2025-GiveAways.lnk` in the Downloads directory:
  - $FILE_NAME creation time: 2025-01-26T16:17:11.271Z (file downloaded)
  - $STANDARD_INFORMATION creation time: 2025-01-26T15:56:20.000Z (original file creation by attacker)
  - $STANDARD_INFORMATION entry time: 2025-01-26T16:17:16.099Z (MFT entry updated)
- The user downloaded the file and executed it ~4 seconds later (16:17:11 -> 16:17:15)
