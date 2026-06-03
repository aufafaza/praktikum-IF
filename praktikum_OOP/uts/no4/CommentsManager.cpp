#include "CommentsManager.hpp"
#include <algorithm>
#include <iostream>
void CommentsManager::kickSpammer(int threshold_score) {
    chat_log_.erase(
        std::remove_if(chat_log_.begin(), chat_log_.end(),
            [&](const Comment& comment) {
                auto it = reputation_.find(comment.getUsername());
		auto score = (it != reputation_.end()) ? it->second : 0;
		return score > threshold_score;
		}
        ),
        chat_log_.end()
    );
}
void CommentsManager::printViolation() {
    bool ada_pelanggaran = false;

    std::for_each(chat_log_.begin(), chat_log_.end(),
        [&](const Comment& comment) {
            std::for_each(comment.getWords().begin(), comment.getWords().end(),
                [&](const std::string& word) {
                    if (blacklist_.count(word)) {
                        ada_pelanggaran = true;
			std::cout << "kata \"" << word << "\" oleh akun \"" << comment.getUsername() << "\"\n";
                    }
                }
            );
        }
    );

    if (!ada_pelanggaran) {
        std::cout << "TIDAK ADA PELANGGARAN\n";
    }
}

std::string CommentsManager::quizWinner(const std::set<std::string>& passwords) {
    auto it = std::find_if(chat_log_.begin(), chat_log_.end(),
        [&](const Comment& comment) {
            return std::find_if(comment.getWords().begin(), comment.getWords().end(),
                [&](const std::string& word) {
                    return passwords.count(word) > 0;
                }
            ) != comment.getWords().end();
        }
    );

    if (it != chat_log_.end()) {
        return it->getUsername();
    }
    return "BELUM ADA PEMENANG";
}
void CommentsManager::upVIPComment() {
    std::stable_partition(chat_log_.begin(), chat_log_.end(),
        [](const Comment& comment) {
            return comment.isVip();
        }
    );
}
